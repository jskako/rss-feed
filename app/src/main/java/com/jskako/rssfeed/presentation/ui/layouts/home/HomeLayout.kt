package com.jskako.rssfeed.presentation.ui.layouts.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jskako.rssfeed.R
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.presentation.state.AddingProcessState
import com.jskako.rssfeed.presentation.ui.components.GridDrawer
import com.jskako.rssfeed.presentation.ui.components.IconButton
import com.jskako.rssfeed.presentation.ui.components.cards.DrawerCard
import com.jskako.rssfeed.presentation.ui.layouts.MessageLayout
import com.jskako.rssfeed.presentation.ui.theme.Padding.l
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.xs
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import kotlinx.coroutines.launch

@Composable
fun HomeLayout(
    navigateToRssManagementScreen: () -> Unit,
    rssChannels: List<RssChannel>,
    rssItems: List<RssItem>,
    selectedChannel: RssChannel?,
    onChannelSelected: (RssChannel) -> Unit,
    updateNotification: (rss: String, isEnabled: Boolean) -> Unit,
    onRefresh: (rss: String, runRssExistCheck: Boolean) -> Unit,
    addingProcessState: AddingProcessState
) {

    var itemsSearchText by remember { mutableStateOf("") }
    var channelsSearchText by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    val filteredChannels = rssChannels.filter { channel ->
        channelsSearchText.isEmpty() ||
                channel.title?.contains(channelsSearchText, ignoreCase = true) == true ||
                channel.rss.contains(channelsSearchText, ignoreCase = true)
    }

    val filteredItems = rssItems.filter { item ->
        itemsSearchText.isEmpty() ||
                item.title?.contains(itemsSearchText, ignoreCase = true) == true ||
                item.description?.contains(itemsSearchText, ignoreCase = true) == true ||
                item.link?.contains(itemsSearchText, ignoreCase = true) == true
    }

    if(selectedChannel != null) {
        GridDrawer(
            modifier = Modifier
                .fillMaxSize(),
            onDrawerContentSearch = {
                channelsSearchText = it
            },
            drawerItems = {
                items(filteredChannels) { channel ->
                    DrawerCard(
                        text = channel.title ?: channel.rss,
                        leadingIcon = channel.imagePath?.let {
                            {
                                AsyncImage(
                                    model = it,
                                    contentDescription = null
                                )
                            }
                        },
                        trailingIcon = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(s)
                            ) {
                                Text(
                                    text = "23",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontSize = 12.sp
                                )

                                IconButton(
                                    icon = Icons.Default.Notifications,
                                    tint = if (channel.notifications) LocalContentColor.current else MaterialTheme.colorScheme.outline,
                                    onClick = {
                                        updateNotification(channel.rss, !channel.notifications)
                                    }
                                )
                            }
                        },
                        isSelected = selectedChannel.rss == channel.rss,
                        onClick = {
                            onChannelSelected(channel)
                        }
                    )
                }
            },
            onGridContentSearch = {
                itemsSearchText = it
            },
            gridItems = {
                items(filteredItems) { item ->
                    Card(
                        modifier = Modifier
                            .padding(xs)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = item.link ?: "",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(l)
                        )
                    }
                }
            },
            drawerTrailingIcon = Icons.Default.Edit,
            onDrawerTrailingIconClick = navigateToRssManagementScreen,
            isRefreshing = addingProcessState == AddingProcessState.FetchingData,
            onPullToRefresh = selectedChannel?.rss?.let {
                {
                    scope.launch {
                        onRefresh(it, false)
                    }
                }
            }
        )
    } else {
        MessageLayout(
            messageResId = R.string.no_rss_selected
        )
    }
}

@PreviewLightDark
@Composable
fun HomeLayoutPreview() {
    RssFeedTheme {
        HomeLayout(
            navigateToRssManagementScreen = {},
            rssChannels = emptyList(),
            updateNotification = { _, _ -> },
            onRefresh = { _, _ -> },
            addingProcessState = AddingProcessState.NotStarted,
            selectedChannel = null,
            rssItems = emptyList(),
            onChannelSelected = {}
        )
    }
}