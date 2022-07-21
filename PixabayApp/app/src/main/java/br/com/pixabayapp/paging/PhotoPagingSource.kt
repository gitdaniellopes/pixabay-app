package br.com.pixabayapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.pixabayapp.domain.model.Photo
import br.com.pixabayapp.domain.source.ImageRemoteDataSource
import java.io.IOException

class PhotoPagingSource(
    private val imageRemoteDataSource: ImageRemoteDataSource,
    private val imageQuery: String
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val page = params.key ?: FIRST_PAGE
            val perPage = params.loadSize

            val data = imageRemoteDataSource.getImages(
                imageQuery = imageQuery,
                page = page,
                perPage = perPage
            )

            LoadResult.Page(
                data = data.photo,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (data.photo.isEmpty()) null else page + 1
            )
        } catch (t: Throwable) {
            var exception = t
            if (t is IOException) {
                exception = IOException("Please check your internet connection and try again")
            }

            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 20
        private const val FIRST_PAGE = 1
    }
}