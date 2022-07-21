package br.com.pixabayapp.domain.source

import br.com.pixabayapp.domain.model.PhotoPaging

interface ImageRemoteDataSource {

    suspend fun getImages(
        imageQuery: String, page: Int,
        perPage: Int
    ): PhotoPaging
}