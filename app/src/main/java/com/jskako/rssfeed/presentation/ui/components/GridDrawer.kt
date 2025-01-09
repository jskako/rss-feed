package com.jskako.rssfeed.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.Padding.l
import com.jskako.rssfeed.presentation.ui.theme.Padding.m
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.xxxl
import com.jskako.rssfeed.presentation.ui.theme.Padding.zero
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridDrawer(
    modifier: Modifier = Modifier,
    drawerValue: DrawerValue = DrawerValue.Closed,
    drawerItems: LazyListScope.() -> Unit,
    gridItems: LazyGridScope.() -> Unit,
    onNavigationContentSearch: ((String) -> Unit)? = null,
    onGridContentSearch: ((String) -> Unit)? = null,
    navigationIconVisible: Boolean = true,
    drawerTrailingIcon: ImageVector? = null,
    @StringRes drawerTrailingIconContentDesc: Int? = null,
    onDrawerTrailingIconClick: (() -> Unit)? = null,
    onRefresh: () -> Unit,
    bottomBar: @Composable () -> Unit = {}
) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = drawerValue)
    val state = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }

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
                        onNavigationContentSearch?.let {
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
                                }
                            ) {
                                Icon(
                                    imageVector = it,
                                    contentDescription = drawerTrailingIconContentDesc?.let {
                                        stringResource(it)
                                    }
                                )
                            }
                        }
                    }
                    LazyColumn { drawerItems() }
                }
            }
        },
    ) {
        Scaffold(
            bottomBar = bottomBar
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                verticalArrangement = Arrangement.spacedBy(s)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (navigationIconVisible) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = stringResource(R.string.menu_content_desc)
                            )
                        }
                    }

                    onGridContentSearch?.let {
                        SearchText(
                            onSearch = it
                        )
                    }
                }
                PullToRefreshBox(
                    state = state,
                    isRefreshing = isRefreshing,
                    onRefresh = {
                        scope.launch {
                            isRefreshing = true
                            onRefresh()
                            delay(5000L)
                            isRefreshing = false
                        }
                    },
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
    }
}

@PreviewLightDark
@Composable
fun GridDrawerPreview() {
    RssFeedTheme {
        val mockedList = List(10) { "MockedString" }
        GridDrawer(
            drawerValue = DrawerValue.Open,
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
            onRefresh = {}
        )
    }
}