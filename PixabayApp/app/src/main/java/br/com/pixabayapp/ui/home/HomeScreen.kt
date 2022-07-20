package br.com.pixabayapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.pixabayapp.domain.model.Photo
import br.com.pixabayapp.ui.home.components.ImageItem
import br.com.pixabayapp.ui.home.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val state = viewModel.state
    val eventFlow = viewModel.eventFlow
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(scaffoldState.snackbarHostState) {
        eventFlow.collect { event ->
            when (event) {
                is HomeViewModel.UIEvent.ShowSnackBar -> event.message?.let {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it
                    )
                }
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            SearchBar(
                modifier = Modifier.padding(8.dp),
                query = state.query,
                onEvent = { viewModel.onEvent(it) },
                getImages = { query -> viewModel.fetch(query) }
            )
        },
        content = { paddingValues ->
            HomeContent(
                modifier = Modifier.padding(paddingValues),
                isLoading = state.isLoading,
                photos = state.photo,
            )
        }
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    photos: List<Photo>,
    isLoading: Boolean = false,
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp)
    ) {
        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Fixed(3),
            content = {
                items(photos) { photo ->
                    ImageItem(
                        item = photo
                    )
                }
            }
        )
        if (isLoading) FullScreenLoading()
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}