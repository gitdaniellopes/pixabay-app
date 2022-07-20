package br.com.pixabayapp.ui.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.pixabayapp.R
import br.com.pixabayapp.ui.home.HomeEvent

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onEvent: (HomeEvent) -> Unit,
    getImages: (String) -> Unit,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = query,
            onValueChange = { onEvent(HomeEvent.EnteredImage(it)) },
            trailingIcon = {
                IconButton(onClick = {
                    getImages(query)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            },
            placeholder = { Text(stringResource(R.string.search_image)) },
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        )
    }
}