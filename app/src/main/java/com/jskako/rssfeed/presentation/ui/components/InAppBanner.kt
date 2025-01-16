package com.jskako.rssfeed.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jskako.rssfeed.presentation.ui.theme.Padding.m
import com.jskako.rssfeed.presentation.ui.theme.defaultBannerColor

@Composable
fun InAppBanner(
    modifier: Modifier = Modifier.padding(m),
    cardColors: CardColors = defaultBannerColor,
    isVisible: Boolean = true,
    @StringRes messageResId: Int,
    icon: ImageVector?
) {
    if (isVisible) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(8.dp),
            colors = cardColors
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = m)
                        .fillMaxWidth()
                        .weight(1f),
                    text = stringResource(messageResId),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )

                icon?.let {
                    Icon(
                        modifier = Modifier.padding(m),
                        imageVector = it,
                        contentDescription = null
                    )
                }
            }
        }
    }
}