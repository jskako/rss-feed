package com.jskako.rssfeed.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.jskako.rssfeed.presentation.ui.theme.Padding.s

@Composable
fun NotificationCircle(
    size: Dp = s,
    color: Color = MaterialTheme.colorScheme.error
) {
    Canvas(
        modifier = Modifier
            .size(size)
    ) {
        drawCircle(
            color = color
        )
    }
}