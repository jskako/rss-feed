package com.jskako.rssfeed.presentation.ui.util.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Tablet Landscape", widthDp = 1920, heightDp = 1080)
@Preview(name = "Tablet Portrait", widthDp = 1080, heightDp = 1920)
@Preview(name = "Phone Landscape", widthDp = 840, heightDp = 360)
@Preview(name = "Small Phone Screen", widthDp = 240, heightDp = 427, locale = "en")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "en")
@Preview("light theme")
annotation class PreviewLightDark
