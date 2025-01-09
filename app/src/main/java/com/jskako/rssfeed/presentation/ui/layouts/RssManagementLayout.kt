package com.jskako.rssfeed.presentation.ui.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.components.ScaffoldTopBar
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun RssManagementLayout(
    navigateBack: () -> Unit
) {
    ScaffoldTopBar(
        title = R.string.rss_management_title,
        onNavigationIconClick = navigateBack
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Text("134")
        }
    }
}

@PreviewLightDark
@Composable
fun RssManagementLayoutPreview() {
    RssFeedTheme {
        RssManagementLayout(
            navigateBack = {}
        )
    }
}