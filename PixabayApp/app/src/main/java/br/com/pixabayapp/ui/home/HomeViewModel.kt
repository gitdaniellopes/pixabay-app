package br.com.pixabayapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pixabayapp.data.source.ResultResource
import br.com.pixabayapp.domain.use_case.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    private var searchJob: Job? = null

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.EnteredImage -> state = state.copy(query = event.value)
        }
    }

    fun fetch(query: String) {
        searchJob?.cancel()
        getImages(query)
    }

    private fun getImages(query: String) {
        viewModelScope.launch {
            getImagesUseCase(
                imageQuery = query
            ).onEach { resultResource ->
                when (resultResource) {
                    is ResultResource.Success -> {
                        resultResource.data?.let { photo ->
                            state = state.copy(
                                photo = photo,
                                isLoading = false
                            )
                        }
                    }
                    is ResultResource.Error -> {
                        state = state.copy(isLoading = false)
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                message = resultResource.message
                            )
                        )
                    }
                    is ResultResource.Empty -> {
                        state = state.copy(isLoading = false)
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                message = resultResource.message
                            )
                        )
                    }
                    is ResultResource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String?) : UIEvent()
    }
}