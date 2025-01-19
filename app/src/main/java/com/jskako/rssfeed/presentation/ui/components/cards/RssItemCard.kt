package com.jskako.rssfeed.presentation.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.jskako.rssfeed.core.utils.toFormattedString
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.model.database.RssItem.Companion.mockerRssItem
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.xs
import com.jskako.rssfeed.presentation.ui.theme.Padding.xxl
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun RssItemCard(
    rssItem: RssItem,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(s),
        elevation = CardDefaults.cardElevation(xs)
    ) {
        Column(
            modifier = Modifier.padding(s),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(s)
            ) {
                rssItem.run {
                    imagePaths?.let {
                        AsyncImage(
                            modifier = Modifier.size(xxl),
                            model = it.first(),
                            contentDescription = null
                        )
                    }

                    guid.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    link?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    updateDate?.let {
                        Text(
                            text = it.toFormattedString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ItemCardPreview() {
    RssFeedTheme {
        RssItemCard(
            rssItem = mockerRssItem,
            onClick = {}
        )
    }
}