package com.jskako.rssfeed.presentation.ui.screens

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.jskako.rssfeed.presentation.state.AddingProcessState
import com.jskako.rssfeed.presentation.ui.layouts.RssManagementLayout
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import com.jskako.rssfeed.presentation.utils.showSnackbarMessage
import com.jskako.rssfeed.presentation.viewmodel.RssViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>
@Composable
fun RssManagementScreen(
    navigator: DestinationsNavigator,
    viewModel: RssViewModel = koinViewModel()
) {

    val rssChannels by viewModel.rssChannels.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val addingProcessState by viewModel.addingProcessState.collectAsState()
    val scope = rememberCoroutineScope()

    addingProcessState.also {
        when (it) {
            is AddingProcessState.Done -> {
                it.result.fold(
                    onSuccess = {

                    },
                    onFailure = { error ->
                        scope.launch {
                            error.message.showSnackbarMessage(snackbarHostState = snackbarHostState)
                        }
                    }
                )
            }

            AddingProcessState.FetchingData -> {}
            AddingProcessState.NotStarted -> {}
        }
    }

    RssManagementLayout(
        navigateBack = {
            navigator.navigateUp()
        },
        snackbarHostState = snackbarHostState,
        rssChannels = rssChannels ?: emptyList(),
        fetchRss = viewModel::fetchRssFeed,
        onDelete = { rssLink ->
            viewModel.deleteRssChannels(rssLink = rssLink)
        }
    )
}

@PreviewLightDark
@Composable
fun RssManagementLayoutPreview() {
    RssFeedTheme {
        RssManagementLayout(
            navigateBack = {},
            rssChannels = emptyList(),
            onDelete = {},
            fetchRss = { _, _, _ -> }
        )
    }
}