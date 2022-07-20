package br.com.pixabayapp.domain.repository

import br.com.pixabayapp.data.source.ResultResource
import br.com.pixabayapp.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

     fun getImages(imageQuery: String): Flow<ResultResource<List<Photo>>>
}