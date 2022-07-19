package br.com.pixabayapp.data.repository

import br.com.pixabayapp.data.source.ResultResource
import br.com.pixabayapp.data.source.remote.PixabayApi
import br.com.pixabayapp.domain.model.Photo
import br.com.pixabayapp.domain.repository.ImageRepository
import java.io.IOException
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val api: PixabayApi
) : ImageRepository {

    override suspend fun getImages(imageQuery: String): ResultResource<Photo> {
        val response = try {
            api.getImages(query = imageQuery)
        } catch (ex: Exception) {
            return ResultResource.Error(message = "Um erro ocorreu", data = null)
        } catch (ex: IOException) {
            return ResultResource.Error(
                message = "Verifique sua conexão com a internet",
                data = null
            )
        }

        val urls: List<String> = response.images.map { imageResult ->
            imageResult.previewURL
        }
        val photo = Photo(previewUrls = urls)
        return ResultResource.Success(photo)
    }
}