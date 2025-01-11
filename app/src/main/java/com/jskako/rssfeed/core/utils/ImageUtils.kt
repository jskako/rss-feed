package com.jskako.rssfeed.core.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun ByteArray.toBitmap(): Bitmap? {
    return runCatching {
        BitmapFactory.decodeByteArray(this, 0, this.size)
    }.getOrNull()
}

fun Bitmap.toByteArray(
    compressionFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 100
): ByteArray? {
    return runCatching {
        ByteArrayOutputStream().use { stream ->
            this.compress(compressionFormat, quality, stream)
            stream.toByteArray()
        }
    }.getOrNull()
}