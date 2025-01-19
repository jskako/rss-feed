package com.jskako.rssfeed.presentation.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.state.AddingProcessState
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
    val addingProcessState by viewModel.addingProcessState.collectAsState()
    val selectedChannel by viewModel.selectedChannel.collectAsState()
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
                    updateNotification = viewModel::updateNotification,
                    onRefresh = viewModel::fetchRssFeed,
                    addingProcessState = addingProcessState,
                    selectedChannel = selectedChannel,
                    rssItems = rssItems,
                    onChannelSelected = viewModel::selectChannel,
                    unreadItemsFlow = viewModel::observeUnreadCount,
                    onItemClick = { link ->
                        link?.let {
                            navigator.navigate(WebViewScreenDestination(url = it))
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
            updateNotification = { _, _ -> },
            onRefresh = { _, _ -> },
            addingProcessState = AddingProcessState.NotStarted,
            selectedChannel = null,
            rssItems = emptyList(),
            onChannelSelected = {},
            unreadItemsFlow = { _ -> flowOf(5) },
            onItemClick = {}
        )
    }
}