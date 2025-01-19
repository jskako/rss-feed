package com.jskako.rssfeed.presentation.ui.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.jskako.rssfeed.R
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.presentation.event.RssEvent
import com.jskako.rssfeed.presentation.state.RssWorkerState
import com.jskako.rssfeed.presentation.ui.components.AddRow
import com.jskako.rssfeed.presentation.ui.components.ScaffoldTopBar
import com.jskako.rssfeed.presentation.ui.components.cards.RssChannelRowCard
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import com.jskako.rssfeed.presentation.utils.showSnackbarMessage
import kotlinx.coroutines.launch

@Composable
fun RssManagementLayout(
    navigateBack: () -> Unit,
    onEvent: (RssEvent) -> Unit,
    rssWorkerState: RssWorkerState,
    rssChannels: List<RssChannel>
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    var input by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(rssWorkerState) {
        when (rssWorkerState) {
            is RssWorkerState.FetchDone -> {
                rssWorkerState.result.fold(
                    onSuccess = {
                        input = ""
                    },
                    onFailure = { error ->
                        scope.launch {
                            error.message.showSnackbarMessage(snackbarHostState = snackbarHostState)
                        }
                    }
                )
            }

            else -> {}
        }
    }

    ScaffoldTopBar(
        titleResId = R.string.rss_management_title,
        snackbarHostState = snackbarHostState,
        onNavigationIconClick = navigateBack
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = s)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(s)
        ) {
            AddRow(
                modifier = Modifier
                    .fillMaxWidth(),
                input = input,
                isRunning = rssWorkerState == RssWorkerState.Running,
                onInputChanged = {
                    input = it
                },
                hintResId = R.string.rss_management_add_hint,
                onIconClick = {
                    keyboardController?.hide()
                    onEvent(
                        RssEvent.FetchRssFeed(
                            rss = it,
                            runRssExistCheck = true,
                            setSelected = true
                        )
                    )
                }
            )

            LazyColumn {
                items(rssChannels) { channel ->
                    RssChannelRowCard(
                        rssChannel = channel,
                        onDeleteConfirmed = {
                            onEvent(
                                RssEvent.DeleteChannel(rss = channel.rss)
                            )
                        }
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun RssManagementLayoutPreview() {
    RssFeedTheme {
        RssManagementLayout(
            navigateBack = {},
            rssChannels = emptyList(),
            rssWorkerState = RssWorkerState.Idle,
            onEvent = {}
        )
    }
}