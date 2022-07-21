package br.com.pixabayapp.ui.home

import androidx.paging.PagingData
import br.com.pixabayapp.domain.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val query: String = "",
    val photos: Flow<PagingData<Photo>> = emptyFlow(),
    val isLoading: Boolean = false,
)