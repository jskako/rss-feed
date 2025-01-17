package com.jskako.rssfeed.core.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Instant.toFormattedString(zoneId: ZoneId = ZoneId.systemDefault()): String {
    val zonedDateTime = this.atZone(zoneId)
    val dateFormatter = DateTimeFormatter.ofPattern(LONG_DATE_PATTERN).withZone(zoneId)

    return zonedDateTime.format(dateFormatter)
}


fun String.toInstantOrNull(): Instant? {
    val formatters = listOf(
        DateTimeFormatter.RFC_1123_DATE_TIME, // For "Fri, 17 Jan 2025 14:29:01 GMT"
        DateTimeFormatter.ISO_INSTANT,        // For "2112-01-01T00:00:00.000+0000" (UTC Instant)
        DateTimeFormatter.ISO_DATE_TIME       // For other ISO-like formats (with or without time zone)
    )

    return formatters.asSequence()
        .mapNotNull { formatter ->
            runCatching {
                Instant.from(formatter.parse(this))
            }.getOrNull()
        }
        .firstOrNull()
}