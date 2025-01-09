package com.jskako.rssfeed.presentation.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.theme.transparentTextFieldColor
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun SearchText(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {

    var searchText by remember { mutableStateOf("") }

    TextField(
        modifier = modifier,
        value = searchText,
        colors = transparentTextFieldColor,
        singleLine = true,
        onValueChange = {
            onSearch(it)
            searchText = it
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search)
            )
        },
    )
}

@PreviewLightDark
@Composable
fun SearchTextPreview() {
    RssFeedTheme {
        SearchText(
            onSearch = {}
        )
    }
}