package br.com.pixabayapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import br.com.pixabayapp.domain.use_case.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.EnteredImage -> state = state.copy(query = event.value)
        }
    }

    fun fetch(query: String) {
        getImages(query)
    }

    private fun getImages(query: String) {
        if (query.isNotEmpty()) {
            val photoFlow = getImagesUseCase(
                imageQuery = query,
                pagingConfig = getPagingConfig()
            ).cachedIn(viewModelScope)
            state = state.copy(photos = photoFlow)
        } else {
            viewModelScope.launch {
                _eventFlow.emit(
                    UIEvent.ShowSnackBar(
                        message = "Preencha o campo pesquisa."
                    )
                )
            }
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String?) : UIEvent()
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = 20
    )
}