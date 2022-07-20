package br.com.pixabayapp.domain.use_case

import br.com.pixabayapp.data.source.ResultResource
import br.com.pixabayapp.domain.model.Photo
import br.com.pixabayapp.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    operator fun invoke(imageQuery: String): Flow<ResultResource<List<Photo>>> {
        return repository.getImages(imageQuery = imageQuery)
    }
}