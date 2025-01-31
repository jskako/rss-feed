package com.jskako.rssfeed.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.Padding.l
import com.jskako.rssfeed.presentation.ui.theme.Padding.m
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.xxxl
import com.jskako.rssfeed.presentation.ui.theme.Padding.zero
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridDrawer(
    modifier: Modifier = Modifier,
    drawerItems: LazyListScope.() -> Unit,
    gridItems: LazyGridScope.() -> Unit,
    onDrawerContentSearch: ((String) -> Unit)? = null,
    onGridContentSearch: ((String) -> Unit)? = null,
    drawerTrailingIcon: ImageVector? = null,
    onDrawerTrailingIconClick: (() -> Unit)? = null,
    gridLeadingIcon: ImageVector? = null,
    onGridLeadingIconClick: (() -> Unit)? = null,
    isRefreshing: Boolean,
    onPullToRefresh: (() -> Unit)?
) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val state = rememberPullToRefreshState()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                windowInsets = WindowInsets(zero)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(s)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        onDrawerContentSearch?.let {
                            SearchText(
                                onSearch = it
                            )
                        }

                        drawerTrailingIcon?.let {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        onDrawerTrailingIconClick?.invoke()
                                    }
                                },
                                icon = it,
                                iconContentDescriptionResId = null
                            )
                        }
                    }
                    LazyColumn { drawerItems() }
                }
            }
        },
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(s)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        icon = Icons.Default.Menu,
                        iconContentDescriptionResId = R.string.menu_content_desc
                    )

                    onGridContentSearch?.let {
                        SearchText(
                            modifier = Modifier.weight(1f),
                            onSearch = it
                        )
                    }

                    gridLeadingIcon?.let {
                        IconButton(
                            onClick = {
                                onGridLeadingIconClick?.invoke()
                            },
                            icon = it,
                            iconContentDescriptionResId = null
                        )
                    }
                }
                PullToRefreshBox(
                    state = state,
                    isRefreshing = isRefreshing && onPullToRefresh != null,
                    onRefresh = { onPullToRefresh?.invoke() },
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(xxxl),
                        contentPadding = PaddingValues(
                            start = m,
                            end = m,
                            bottom = l
                        ),
                        content = { gridItems() }
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
fun GridDrawerPreview() {
    RssFeedTheme {
        val mockedList = List(10) { "MockedString" }
        GridDrawer(
            drawerItems = {
                items(mockedList) { item ->
                    Text(item)
                }
            },
            gridItems = {
                items(mockedList) { item ->
                    Text(item)
                }
            },
            isRefreshing = false,
            onPullToRefresh = {}
        )
    }
}