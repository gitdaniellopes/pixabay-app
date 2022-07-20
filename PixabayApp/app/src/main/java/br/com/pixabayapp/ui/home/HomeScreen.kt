package br.com.pixabayapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.pixabayapp.domain.model.Photo
import br.com.pixabayapp.ui.home.components.ImageItem
import br.com.pixabayapp.ui.home.components.SearchBar

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            SearchBar(modifier = Modifier.padding(8.dp))
        },
        content = { paddingValues ->
            HomeContent(
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    photo: List<Photo> = emptyList()
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(80.dp),
            content = {
                items(100) {
                    ImageItem(
                        item = Photo(
                            previewUrls = listOf(
                                "https://imagens-cdn.canalrural.com.br/2019/11/frutas-hortalic%CC%A7as.jpeg",
                                "https://www.portalsaudenoar.com.br/wp-content/uploads/2015/10/Frutas.jpg",
                                "https://s2.glbimg.com/WtZG8KPi9LK10sLTo2oZO7xwC5s=/512x320/smart/e.glbimg.com/og/ed/f/original/2015/02/05/fruit-333.jpg"
                            )
                        ),
                    )
                }
            }
        )
    }
}

@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(
        navController = navController
    )
}