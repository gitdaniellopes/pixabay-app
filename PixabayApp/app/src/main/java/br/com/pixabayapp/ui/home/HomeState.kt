package br.com.pixabayapp.ui.home

import br.com.pixabayapp.domain.model.Photo

data class HomeState(
    val query: String = "",
    val photo: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
)