package com.jskako.rssfeed.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import kotlinx.coroutines.launch

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
) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = drawerValue)

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                windowInsets = WindowInsets(0.dp)
            ) {
                Text("123")
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        onNavigationContentSearch?.let {
                            SearchText(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                onSearch = it
                            )
                        }

                        drawerTrailingIcon?.let {
                            IconButton(
                                modifier = Modifier
                                    .weight(1f),
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
            bottomBar = {
                Text("123")
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
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

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(256.dp),
                    contentPadding = PaddingValues(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 16.dp
                    ),
                    content = { gridItems() }
                )
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
            }
        )
    }
}