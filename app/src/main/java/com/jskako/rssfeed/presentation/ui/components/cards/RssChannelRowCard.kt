package com.jskako.rssfeed.presentation.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jskako.rssfeed.R
import com.jskako.rssfeed.core.utils.toFormattedString
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssChannel.Companion.mockedRssChannel
import com.jskako.rssfeed.presentation.ui.components.IconButton
import com.jskako.rssfeed.presentation.ui.components.TextDialog
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.xs
import com.jskako.rssfeed.presentation.ui.theme.Padding.xxl
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun RssChannelRowCard(
    rssChannel: RssChannel,
    onDeleteConfirmed: () -> Unit
) {

    var deleteDialogVisible by remember { mutableStateOf(false) }

    if (deleteDialogVisible) {
        TextDialog(
            titleResId = R.string.delete,
            descriptionResId = R.string.delete_desc,
            onConfirmRequest = {
                deleteDialogVisible = false
                onDeleteConfirmed()
            },
            onDismissRequest = {
                deleteDialogVisible = false
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(xs),
        elevation = CardDefaults.cardElevation(xs)
    ) {
        Column(
            modifier = Modifier.padding(s),
            verticalArrangement = Arrangement.Center
        ) {
            rssChannel.run {
                Column(
                    verticalArrangement = Arrangement.spacedBy(s)
                ) {
                    imagePath?.let {
                        AsyncImage(
                            modifier = Modifier.size(xxl),
                            model = it,
                            contentDescription = null
                        )
                    }

                    title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = rss,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 12.sp
                    )

                    link?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 12.sp
                        )
                    }

                    lastBuildDate?.let {
                        Text(
                            text = it.toFormattedString(),
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    icon = Icons.Default.Delete,
                    onClick = {
                        deleteDialogVisible = true
                    }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun DeleteEditRowCardPreview() {
    RssFeedTheme {
        RssChannelRowCard(
            rssChannel = mockedRssChannel,
            onDeleteConfirmed = {}
        )
    }
}