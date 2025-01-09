package com.jskako.rssfeed.presentation.ui.util.viewdata

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

val isPortrait: Boolean
    @Composable get() = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
