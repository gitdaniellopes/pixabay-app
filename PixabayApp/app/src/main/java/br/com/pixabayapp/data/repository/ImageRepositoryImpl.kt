package br.com.pixabayapp.data.repository

import br.com.pixabayapp.data.source.ResultResource
import br.com.pixabayapp.data.source.remote.PixabayApi
import br.com.pixabayapp.domain.model.Photo
import br.com.pixabayapp.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val api: PixabayApi
) : ImageRepository {

    override fun getImages(imageQuery: String): Flow<ResultResource<List<Photo>>> = flow {
        emit(ResultResource.Loading())
        try {
            val response = api.getImages(query = imageQuery)
            val photos = response.images.map { Photo(previewUrl = it.previewURL) }
            if (photos.isEmpty()) {
                emit(ResultResource.Empty(message = "Nenhuma imagem encontrada."))
            } else {
                emit(ResultResource.Success(photos))
            }
        } catch (ex: HttpException) {
            emit(ResultResource.Error(message = "Um erro ocorreu", data = null))
        } catch (ex: IOException) {
            emit(
                ResultResource.Error(
                    message = "Verifique sua conexão com a internet",
                    data = null
                )
            )
        }
    }
}