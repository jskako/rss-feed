package com.jskako.rssfeed.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun GridDrawer(
    modifier: Modifier = Modifier,
    drawerValue: DrawerValue = DrawerValue.Closed,
    drawerContent: LazyListScope.() -> Unit,
    gridContent: LazyGridScope.() -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = drawerValue)

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                LazyColumn { drawerContent() }
            }
        },
    ) {
        Scaffold(
            bottomBar = {
                Text("123")
            }
        ) { paddingValues ->
            LazyVerticalGrid(
                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                columns = GridCells.Adaptive(256.dp),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                content = { gridContent() }
            )
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
            drawerContent = {
                items(mockedList) { item ->
                    Text(item)
                }
            },
            gridContent = {
                items(mockedList) { item ->
                    Text(item)
                }
            }
        )
    }
}