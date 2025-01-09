package com.jskako.rssfeed.presentation.ui.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.components.AddRow
import com.jskako.rssfeed.presentation.ui.components.ScaffoldTopBar
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun RssManagementLayout(
    navigateBack: () -> Unit
) {
    ScaffoldTopBar(
        titleResId = R.string.rss_management_title,
        onNavigationIconClick = navigateBack
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = s)
                .fillMaxSize()
        ) {
            AddRow(
                modifier = Modifier
                    .fillMaxWidth(),
                hintResId = R.string.rss_management_add_hint,
                onIconClick = {

                }
            )
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