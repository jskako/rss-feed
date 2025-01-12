package com.jskako.rssfeed.presentation.ui.layouts.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.jskako.rssfeed.R
import com.jskako.rssfeed.presentation.ui.components.Button
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.adaptiveImageSize
import com.jskako.rssfeed.presentation.ui.util.isInPortraitOrientation
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun HomeEmptyLayout(
    navigateToRssManagementScreen: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(s)
        ) {
            Image(
                modifier = Modifier
                    .adaptiveImageSize(isInPortraitOrientation())
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.cat),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                contentDescription = stringResource(R.string.cat_content_desc)
            )

            Text(
                modifier = Modifier.padding(top = s),
                text = stringResource(R.string.rss_empty)
            )

            Button(
                text = stringResource(R.string.add_rss_url),
                onClick = { navigateToRssManagementScreen() }
            )
        }
    }
}

@PreviewLightDark
@Composable
fun HomeEmptyLayoutPreview() {
    RssFeedTheme {
        HomeEmptyLayout(
            navigateToRssManagementScreen = {}
        )
    }
}