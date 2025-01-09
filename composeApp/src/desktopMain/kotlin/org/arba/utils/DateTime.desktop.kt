package org.arba.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toKotlinInstant
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

actual object DateTime {
    actual fun LocalDateTime.format(format: String): String {
        val dateFormatter = DateTimeFormatter.ofPattern(format)
        return this.format(dateFormatter.toString())
    }

    actual fun getDateTime(string: String, format: String): LocalDateTime {
//        val dateFormatter = DateTimeFormatter.ofPattern(format)
////        return LocalDateTime.parse(string, dateFormatter)
//        val date = dateFormatter.parse(string)
//            ?: throw IllegalStateException("Could not convert string to NSDate $string")
//        return date.toKotlinInstant().toLocalDateTime(TimeZone.currentSystemDefault())

        return throw IllegalArgumentException("")
    }
}