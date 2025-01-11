package com.jskako.rssfeed.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.jskako.rssfeed.presentation.ui.layouts.RssManagementLayout
import com.jskako.rssfeed.presentation.ui.navigation.mocks.mockNavigator
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import com.jskako.rssfeed.presentation.viewmodel.RssViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>
@Composable
fun RssManagementScreen(
    navigator: DestinationsNavigator,
    viewModel: RssViewModel = koinViewModel()
) {

    val rssFeeds by viewModel.rssFeeds.collectAsState()

    RssManagementLayout(
        navigateBack = {
            navigator.navigateUp()
        },
        rssFeeds = rssFeeds
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