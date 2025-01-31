package com.jskako.rssfeed.presentation.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.event.RssEvent
import com.jskako.rssfeed.presentation.state.RssWorkerState
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
import com.ramcosta.composedestinations.generated.destinations.WebViewScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: RssViewModel = koinViewModel(),
    networkViewModel: NetworkViewModel = koinViewModel()
) {

    BackHandler { }

    val rssChannels by viewModel.rssChannels.collectAsState()
    val rssWorkerState by viewModel.rssWorkerState.collectAsState()
    val selectedRss by viewModel.selectedRss.collectAsState()
    val rssItems by viewModel.rssItems.collectAsState()

    if (rssChannels != null) {

        val isConnected by networkViewModel.isConnected.collectAsState()

        Column {
            InAppBanner(
                isVisible = !isConnected,
                messageResId = R.string.offline_mode_banner,
                icon = Icons.Default.Warning
            )

            when {
                rssChannels?.isNotEmpty() == true -> HomeLayout(
                    navigateToRssManagementScreen = {
                        navigator.navigate(
                            RssManagementScreenDestination()
                        )
                    },
                    rssChannels = rssChannels ?: emptyList(),
                    rssWorkerState = rssWorkerState,
                    selectedRss = selectedRss,
                    rssItems = rssItems,
                    unreadItemsFlow = viewModel::observeUnreadCount,
                    onEvent = { event ->
                        viewModel.onRssEvent(event = event)
                    },
                    onItemClick = { guid, link ->
                        if (isConnected) {
                            viewModel.onRssEvent(
                                RssEvent.HasBeenRead(guid = guid)
                            )
                            link?.let {
                                navigator.navigate(WebViewScreenDestination(url = it))
                            }
                        }
                    }
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
}

@PreviewLightDark
@Composable
fun HomeScreenPreview() {
    RssFeedTheme {
        HomeLayout(
            navigateToRssManagementScreen = {},
            rssChannels = emptyList(),
            rssWorkerState = RssWorkerState.Idle,
            selectedRss = null,
            rssItems = emptyList(),
            unreadItemsFlow = { _ -> flowOf(5) },
            onItemClick = { _, _ -> },
            onEvent = {}
        )
    }
}