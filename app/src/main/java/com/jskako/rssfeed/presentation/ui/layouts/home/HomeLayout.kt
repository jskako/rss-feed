package com.jskako.rssfeed.presentation.ui.layouts.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jskako.rssfeed.R
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.presentation.event.RssEvent
import com.jskako.rssfeed.presentation.state.RssWorkerState
import com.jskako.rssfeed.presentation.ui.components.GridDrawer
import com.jskako.rssfeed.presentation.ui.components.IconButton
import com.jskako.rssfeed.presentation.ui.components.ScaffoldTopBar
import com.jskako.rssfeed.presentation.ui.components.cards.DrawerCard
import com.jskako.rssfeed.presentation.ui.components.cards.RssItemCard
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
fun HomeLayout(
    navigateToRssManagementScreen: () -> Unit,
    onEvent: (RssEvent) -> Unit,
    rssChannels: List<RssChannel>,
    rssItems: List<RssItem>,
    selectedRss: String?,
    onItemClick: (guid: String, link: String?) -> Unit,
    rssWorkerState: RssWorkerState,
    unreadItemsFlow: (String) -> Flow<Int>
) {

    var itemsSearchText by remember { mutableStateOf("") }
    var channelsSearchText by remember { mutableStateOf("") }
    var filterFavorites by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val filteredChannels = rssChannels.filter { channel ->
        channelsSearchText.isEmpty() ||
                channel.title?.contains(channelsSearchText, ignoreCase = true) == true ||
                channel.rss.contains(channelsSearchText, ignoreCase = true)
    }

    val filteredItems = rssItems.filter { item ->
        val matchesSearchText = itemsSearchText.isEmpty() ||
                item.title?.contains(itemsSearchText, ignoreCase = true) == true ||
                item.description?.contains(itemsSearchText, ignoreCase = true) == true ||
                item.link?.contains(itemsSearchText, ignoreCase = true) == true

        val matchesFavorites = !filterFavorites || item.isFavorite

        matchesSearchText && matchesFavorites
    }

    ScaffoldTopBar(
        topBar = null,
        snackbarHostState = snackbarHostState
    ) {
        GridDrawer(
            modifier = Modifier
                .fillMaxSize(),
            onDrawerContentSearch = {
                channelsSearchText = it
            },
            drawerItems = {
                items(filteredChannels) { channel ->

                    val unreadCount by unreadItemsFlow(channel.rss).collectAsState(initial = 0)

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
                                    text = unreadCount.toString(),
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontSize = 12.sp
                                )

                                IconButton(
                                    icon = Icons.Default.Notifications,
                                    tint = if (channel.notifications) LocalContentColor.current else MaterialTheme.colorScheme.outline,
                                    onClick = {
                                        scope.launch {
                                            channel.notifications.also { notificationEnabled ->
                                                onEvent(
                                                    if (notificationEnabled) {
                                                        RssEvent.CancelWork(rss = channel.rss)
                                                    } else {
                                                        RssEvent.ScheduleWork(rss = channel.rss)
                                                    }
                                                )

                                                onEvent(
                                                    RssEvent.UpdateNotification(
                                                        rss = channel.rss,
                                                        isEnabled = !notificationEnabled
                                                    )
                                                )

                                                snackbarHostState.showSnackbar(
                                                    message = context.getString(
                                                        if (!notificationEnabled) {
                                                            R.string.rss_notification_enabled
                                                        } else {
                                                            R.string.rss_notification_disabled
                                                        }
                                                    )
                                                )
                                            }
                                        }
                                    }
                                )
                            }
                        },
                        isSelected = selectedRss == channel.rss,
                        onClick = {
                            onEvent(
                                RssEvent.SelectChannel(rss = channel.rss)
                            )
                        }
                    )
                }
            },
            onGridContentSearch = {
                itemsSearchText = it
            },
            gridItems = {
                items(filteredItems) { item ->
                    RssItemCard(
                        rssItem = item,
                        onClick = {
                            onItemClick(item.guid, item.link)
                        },
                        onFavorite = {
                            onEvent(
                                RssEvent.UpdateFavorite(
                                    guid = item.guid,
                                    isFavorite = !item.isFavorite
                                )
                            )
                        }
                    )
                }
            },
            drawerTrailingIcon = Icons.Default.Edit,
            onDrawerTrailingIconClick = navigateToRssManagementScreen,
            gridLeadingIcon = if (filterFavorites) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            onGridLeadingIconClick = {
                filterFavorites = !filterFavorites
            },
            isRefreshing = rssWorkerState == RssWorkerState.Running,
            onPullToRefresh = selectedRss?.let {
                {
                    onEvent(
                        RssEvent.FetchRssFeed(
                            rss = it, runRssExistCheck = false
                        )
                    )
                }
            }
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
            rssWorkerState = RssWorkerState.Idle,
            selectedRss = null,
            rssItems = emptyList(),
            unreadItemsFlow = { _ -> flowOf(5) },
            onItemClick = { _, _ -> },
            onEvent = {}
        )
    }
}