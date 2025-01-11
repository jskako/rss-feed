package com.jskako.rssfeed.presentation.ui.util

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier

fun Modifier.adaptiveImageSize(
    isPortrait: Boolean,
    size: Float = 0.5f
): Modifier {
    return if (isPortrait) {
        this.fillMaxWidth(size)
    } else {
        this.fillMaxHeight(size)
    }
}