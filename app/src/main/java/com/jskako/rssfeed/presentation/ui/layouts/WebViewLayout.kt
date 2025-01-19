package com.jskako.rssfeed.presentation.ui.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jskako.rssfeed.presentation.ui.components.ScaffoldTopBar
import com.jskako.rssfeed.presentation.ui.components.WebView
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun WebViewLayout(
    navigateBack: () -> Unit,
    url: String
) {
    ScaffoldTopBar(
        onNavigationIconClick = navigateBack
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
        ) {
            WebView(url = url)
        }
    }
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