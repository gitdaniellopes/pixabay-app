package br.com.pixabayapp.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.pixabayapp.domain.model.Photo
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    item: Photo?
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 2.dp),
        shape = RoundedCornerShape(corner = CornerSize(2.dp))
    ) {
        ImageContainer(
            modifier = Modifier.fillMaxSize(),
            content = {
                if (item != null) {
                    ImageUrl(item = item)
                }
            }
        )
    }
}

@Composable
fun ImageContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.aspectRatio(1f),
        RoundedCornerShape(4.dp)
    ) {
        content()
    }
}


@Composable
fun ImageUrl(item: Photo) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.previewUrl)
                .crossfade(true)
                .size(Size.ORIGINAL)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}