package br.com.pixabayapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.pixabayapp.domain.model.Photo
import br.com.pixabayapp.ui.home.components.ImageItem
import br.com.pixabayapp.ui.home.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val photos = viewModel.state.photos.collectAsLazyPagingItems()
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
                photos = photos
            )
        }
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    photos: LazyPagingItems<Photo>,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 4.dp, end = 4.dp),
        contentAlignment = Alignment.Center,
    )
    {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            content = {
                items(photos.itemCount) { index ->
                    photos[index]?.let { photo ->
                        ImageItem(
                            item = photo
                        )
                    }
                }
                photos.apply {
                    when {
                        loadState.append is LoadState.Loading -> {
                            item { LoadingItem() }
                        }
                        loadState.refresh is LoadState.Loading -> {
                            item { LoadingView() }
                        }
                        loadState.refresh is LoadState.Error -> {
                            val loadStateError = photos.loadState.refresh as LoadState.Error
                            item {
                                ErrorItem(
                                    modifier = modifier.wrapContentWidth(),
                                    message = loadStateError.error.localizedMessage!!,
                                    onClickRetry = { retry() }
                                )
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            val loadStateError = photos.loadState.append as LoadState.Error
                            item {
                                ErrorItem(
                                    modifier = modifier.wrapContentWidth(),
                                    message = loadStateError.error.localizedMessage!!,
                                    onClickRetry = { retry() }
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}