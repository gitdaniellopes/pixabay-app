package br.com.pixabayapp.data.repository

import androidx.paging.PagingSource
import br.com.pixabayapp.domain.model.Photo
import br.com.pixabayapp.domain.repository.ImageRepository
import br.com.pixabayapp.domain.source.ImageRemoteDataSource
import br.com.pixabayapp.paging.PhotoPagingSource
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val dataSource: ImageRemoteDataSource
) : ImageRepository {

    override fun getImages(imageQuery: String): PagingSource<Int, Photo> {
        return PhotoPagingSource(dataSource, imageQuery)
    }
}