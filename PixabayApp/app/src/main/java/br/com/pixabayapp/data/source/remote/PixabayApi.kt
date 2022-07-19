package br.com.pixabayapp.data.source.remote

import br.com.pixabayapp.BuildConfig
import br.com.pixabayapp.data.source.remote.response.ImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun getImages(
        @Query("q") query: String? = null,
        @Query("key") apiKey: String = BuildConfig.API_KEY,
    ): ImagesResponse
}