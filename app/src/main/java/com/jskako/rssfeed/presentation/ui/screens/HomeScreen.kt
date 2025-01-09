package com.jskako.rssfeed.presentation.ui.screens

import androidx.compose.runtime.Composable
import com.jskako.rssfeed.presentation.ui.sections.home.HomeSection
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Destination<RootGraph>(start = true)
@Composable
fun HomeScreen() {

    val testList = List(100) { "SomeLink" }

    when {
        testList.isNotEmpty() -> {
            HomeSection(
                drawerList = testList,
                gridList = testList
            )
        }

        else -> {
        }
    }
}

@PreviewLightDark
@Composable
fun HomeScreenPreview() {
    RssFeedTheme {
        HomeScreen()
    }
}