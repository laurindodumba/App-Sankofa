package com.example.sankofa.utlis

import java.time.Instant
import java.time.ZoneId

fun formatDateTimeAndPlace(millis: Long, location: String): String {
    val dt = Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()

    val date = "%02d/%02d/%04d".format(dt.dayOfMonth, dt.monthValue, dt.year)
    val time = "%02d:%02d".format(dt.hour, dt.minute)

    return "$date • $time • $location"
}
