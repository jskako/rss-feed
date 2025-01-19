package com.jskako.rssfeed.presentation.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.jskako.rssfeed.R
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.model.database.RssItem.Companion.mockerRssItem
import com.jskako.rssfeed.presentation.ui.components.IconButton
import com.jskako.rssfeed.presentation.ui.components.ImageSlider
import com.jskako.rssfeed.presentation.ui.components.NotificationCircle
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.xs
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun RssItemCard(
    rssItem: RssItem,
    onClick: () -> Unit,
    onFavorite: () -> Unit
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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(s)
                    ) {
                        if (!hasBeenRead) {
                            NotificationCircle()

                            Text(
                                text = stringResource(R.string.unread_article),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            icon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            tint = MaterialTheme.colorScheme.error,
                            onClick = onFavorite
                        )
                    }

                    title?.trim()?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    imagePaths?.let {
                        ImageSlider(
                            imageUrls = it
                        )
                    }

                    description?.trim()?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium
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
            onClick = {},
            onFavorite = {}
        )
    }
}