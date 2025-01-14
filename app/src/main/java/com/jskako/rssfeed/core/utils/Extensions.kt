package com.jskako.rssfeed.core.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Instant.toFormattedString(zoneId: ZoneId = ZoneId.systemDefault()): String {
    val zonedDateTime = this.atZone(zoneId)
    val dateFormatter = DateTimeFormatter.ofPattern(LONG_DATE_PATTERN).withZone(zoneId)

    return zonedDateTime.format(dateFormatter)
}

private const val LONG_DATE_PATTERN = "dd.MM.yyyy HH:mm:ss"