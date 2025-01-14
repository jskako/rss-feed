package com.jskako.rssfeed.presentation.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.jskako.rssfeed.presentation.ui.layouts.home.HomeEmptyLayout
import com.jskako.rssfeed.presentation.ui.layouts.home.HomeLayout
import com.jskako.rssfeed.presentation.ui.navigation.mocks.mockNavigator
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import com.jskako.rssfeed.presentation.viewmodel.NetworkViewModel
import com.jskako.rssfeed.presentation.viewmodel.RssViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.RssManagementScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: RssViewModel = koinViewModel(),
    networkViewModel: NetworkViewModel = koinViewModel()
) {

    val rssItems by viewModel.rssChannels.collectAsState()
    val isConnected by networkViewModel.isConnected.collectAsState()

    val gridList = List(100) { "SomeLink" }

    LaunchedEffect(Unit) {
        viewModel.testFetch()
    }

    when {
        rssItems.isNotEmpty() -> HomeLayout(
            navigateToRssManagementScreen = {
                navigator.navigate(
                    RssManagementScreenDestination()
                )
            },
            drawerList = rssItems,
            gridList = gridList
        )

        else -> HomeEmptyLayout(
            navigateToRssManagementScreen = {
                navigator.navigate(
                    RssManagementScreenDestination()
                )
            }
        )
    }
}

@PreviewLightDark
@Composable
fun HomeScreenPreview() {
    RssFeedTheme {
        HomeScreen(
            navigator = mockNavigator
        )
    }
}