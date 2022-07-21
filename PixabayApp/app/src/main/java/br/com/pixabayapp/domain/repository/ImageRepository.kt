package br.com.pixabayapp.domain.repository

import androidx.paging.PagingSource
import br.com.pixabayapp.domain.model.Photo

interface ImageRepository {

    fun getImages(imageQuery: String): PagingSource<Int, Photo>
}