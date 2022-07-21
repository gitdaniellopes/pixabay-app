package br.com.pixabayapp.domain.model

data class PhotoPaging(
    val page: Int,
    val perPage: Int,
    val photo: List<Photo>
)