package br.com.pixabayapp.ui.home

sealed class HomeEvent {
    data class EnteredImage(val value: String): HomeEvent()
}