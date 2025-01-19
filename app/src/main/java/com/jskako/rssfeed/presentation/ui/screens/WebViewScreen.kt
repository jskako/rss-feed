package com.jskako.rssfeed.presentation.ui.screens

import androidx.compose.runtime.Composable
import com.jskako.rssfeed.presentation.ui.layouts.WebViewLayout
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun WebViewScreen(
    navigator: DestinationsNavigator,
    url: String
) {
    WebViewLayout(
        navigateBack = { navigator.navigateUp() },
        url = url
    )
}

@PreviewLightDark
@Composable
fun HomeEmptyLayoutPreview() {
    RssFeedTheme {
        WebViewLayout(
            navigateBack = {},
            url = ""
        )
    }
}