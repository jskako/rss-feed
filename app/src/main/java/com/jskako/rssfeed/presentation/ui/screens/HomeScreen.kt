package com.jskako.rssfeed.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Destination<RootGraph>(start = true)
@Composable
fun HomeScreen() {
    Column {
        Text(
            text = "Test"
        )
    }
}

@PreviewLightDark
@Composable
fun FaqMoreInfoLayoutPreview() {
    RssFeedTheme {
        HomeScreen()
    }
}