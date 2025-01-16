package com.jskako.rssfeed.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.components.InAppBanner
import com.jskako.rssfeed.presentation.ui.layouts.home.HomeEmptyLayout
import com.jskako.rssfeed.presentation.ui.layouts.home.HomeLayout
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import com.jskako.rssfeed.presentation.viewmodel.NetworkViewModel
import com.jskako.rssfeed.presentation.viewmodel.RssViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.RssManagementScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: RssViewModel = koinViewModel(),
    networkViewModel: NetworkViewModel = koinViewModel()
) {

    val rssChannels by viewModel.rssChannels.collectAsState()
    val isConnected by networkViewModel.isConnected.collectAsState()
    val gridList = List(100) { "SomeLink" }

    Column {
        InAppBanner(
            isVisible = !isConnected,
            messageResId = R.string.offline_mode_banner,
            icon = Icons.Default.Warning
        )

        when {
            rssChannels.isNotEmpty() -> HomeLayout(
                navigateToRssManagementScreen = {
                    navigator.navigate(
                        RssManagementScreenDestination()
                    )
                },
                drawerList = rssChannels,
                gridList = gridList
            )

            else -> HomeEmptyLayout(
                isConnected = isConnected,
                navigateToRssManagementScreen = {
                    navigator.navigate(
                        RssManagementScreenDestination()
                    )
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
fun HomeScreenPreview() {
    RssFeedTheme {
        HomeLayout(
            navigateToRssManagementScreen = {},
            drawerList = emptyList(),
            gridList = emptyList()
        )
    }
}