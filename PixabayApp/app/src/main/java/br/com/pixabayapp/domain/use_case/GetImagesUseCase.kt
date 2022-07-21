package br.com.pixabayapp.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.pixabayapp.domain.model.Photo
import br.com.pixabayapp.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    operator fun invoke(imageQuery: String, pagingConfig: PagingConfig): Flow<PagingData<Photo>> {
        val pagingSource = repository.getImages(imageQuery = imageQuery)
        return Pager(config = pagingConfig) {
            pagingSource
        }.flow
    }
}