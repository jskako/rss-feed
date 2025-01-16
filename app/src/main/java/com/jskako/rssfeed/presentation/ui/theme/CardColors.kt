package com.jskako.rssfeed.presentation.ui.theme

import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

val defaultBannerColor: CardColors
    @Composable
    get() = CardDefaults.cardColors(
        contentColor = MaterialTheme.colorScheme.surface,
        containerColor = MaterialTheme.colorScheme.error
    )
