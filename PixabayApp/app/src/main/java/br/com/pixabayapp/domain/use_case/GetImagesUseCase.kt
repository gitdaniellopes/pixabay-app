package br.com.pixabayapp.domain.use_case

import br.com.pixabayapp.data.source.ResultResource
import br.com.pixabayapp.domain.model.Photo
import br.com.pixabayapp.domain.repository.ImageRepository
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(imageQuery: String): ResultResource<Photo> {
        return repository.getImages(imageQuery = imageQuery)
    }
}