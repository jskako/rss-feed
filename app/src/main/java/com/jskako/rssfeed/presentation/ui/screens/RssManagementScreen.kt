package com.jskako.rssfeed.presentation.ui.screens

import androidx.compose.runtime.Composable
import com.jskako.rssfeed.presentation.ui.layouts.RssManagementLayout
import com.jskako.rssfeed.presentation.ui.navigation.mocks.mockNavigator
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun RssManagementScreen(
    navigator: DestinationsNavigator
) {
    RssManagementLayout(
        navigateBack = {
            navigator.navigateUp()
        }
    )
}

@PreviewLightDark
@Composable
fun RssManagementScreenPreview() {
    RssFeedTheme {
        RssManagementScreen(
            navigator = mockNavigator
        )
    }
}