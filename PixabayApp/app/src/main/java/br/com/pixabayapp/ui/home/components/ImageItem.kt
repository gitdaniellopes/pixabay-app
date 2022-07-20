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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.pixabayapp.R
import br.com.pixabayapp.domain.model.Photo
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    item: Photo
) {
    Card(
        modifier = modifier
            .size(64.dp)
            .padding(all = 2.dp),
        shape = RoundedCornerShape(corner = CornerSize(2.dp))
    ) {
        ImageContainer(
            modifier = Modifier.size(64.dp),
            content = {
                ImageUrl(item = item)
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
        modifier = Modifier.height(64.dp).width(64.dp),
        contentAlignment = Alignment.Center
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.previewUrls)
                .crossfade(true)
                .size(Size.ORIGINAL)
                .build()
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ImageItemPreview() {
    ImageItem(
        item = Photo(
            previewUrls =
            listOf(
                "https://imagens-cdn.canalrural.com.br/2019/11/frutas-hortalic%CC%A7as.jpeg",
                "https://www.portalsaudenoar.com.br/wp-content/uploads/2015/10/Frutas.jpg",
                "https://s2.glbimg.com/WtZG8KPi9LK10sLTo2oZO7xwC5s=/512x320/smart/e.glbimg.com/og/ed/f/original/2015/02/05/fruit-333.jpg"
            )
        )
    )
}