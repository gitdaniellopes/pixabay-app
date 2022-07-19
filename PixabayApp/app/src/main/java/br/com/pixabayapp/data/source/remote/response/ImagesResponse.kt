package br.com.pixabayapp.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ImagesResponse(
    @SerializedName("hits")
    val images: List<Image>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
)