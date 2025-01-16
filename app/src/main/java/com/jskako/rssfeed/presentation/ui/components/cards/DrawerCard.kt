package com.jskako.rssfeed.presentation.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.jskako.rssfeed.presentation.ui.theme.Padding.s
import com.jskako.rssfeed.presentation.ui.theme.Padding.xs
import com.jskako.rssfeed.presentation.ui.theme.RssFeedTheme
import com.jskako.rssfeed.presentation.ui.util.preview.PreviewLightDark

@Composable
fun DrawerCard(
    text: String,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(s),
        elevation = CardDefaults.cardElevation(xs),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.error else Color.Unspecified
        )
    ) {
        Box(
            modifier = Modifier.padding(s)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(s)
            ) {
                leadingIcon?.invoke()

                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                trailingIcon?.invoke()
            }
        }
    }
}

@PreviewLightDark
@Composable
fun DrawerCardPreview() {
    RssFeedTheme {
        DrawerCard(
            text = "",
            isSelected = false,
            onClick = {}
        )
    }
}