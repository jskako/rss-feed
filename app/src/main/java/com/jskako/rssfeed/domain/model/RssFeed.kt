package com.jskako.rssfeed.domain.model

import android.graphics.Bitmap

data class RssFeed(
    val url: String,
    val description: String?,
    val image: Bitmap?
)