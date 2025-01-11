package com.jskako.rssfeed.presentation.ui.util

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun isInPortraitOrientation() =
    LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT