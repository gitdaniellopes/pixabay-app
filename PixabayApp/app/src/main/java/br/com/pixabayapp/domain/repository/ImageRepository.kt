package br.com.pixabayapp.domain.repository

import br.com.pixabayapp.data.source.ResultResource
import br.com.pixabayapp.domain.model.Photo

interface ImageRepository {

    suspend fun getImages(imageQuery: String): ResultResource<Photo>
}