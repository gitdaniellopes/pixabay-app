package br.com.pixabayapp.data.source

import br.com.pixabayapp.data.source.remote.PixabayApi
import br.com.pixabayapp.domain.model.Photo
import br.com.pixabayapp.domain.model.PhotoPaging
import br.com.pixabayapp.domain.source.ImageRemoteDataSource
import javax.inject.Inject

class ImageRemoteDataSourceImpl @Inject constructor(
    private val api: PixabayApi
) : ImageRemoteDataSource {
    override suspend fun getImages(imageQuery: String, page: Int, perPage: Int): PhotoPaging {
        val response = api.getImages(
            query = imageQuery,
            page = page,
            per_page = perPage
        )
        val photos = response.images.map { Photo(previewUrl = it.previewURL) }

        return PhotoPaging(
            page = page,
            perPage = perPage,
            photo = photos
        )
    }
}