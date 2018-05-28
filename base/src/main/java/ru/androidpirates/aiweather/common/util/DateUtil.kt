package ru.androidpirates.aiweather.common.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun now(format: String): String {
        return formatter(format).format(Date())
    }

    fun format(format: String, date: Date): String {
        return formatter(format).format(date)
    }

    @Throws(ParseException::class)
    fun parse(format: String, source: String): Date {
        return formatter(format).parse(source)
    }

    fun parseSafety(format: String, source: String): Date? {
        return try {
            formatter(format).parse(source)
        } catch (e: ParseException) {
            null
        }
    }

    private fun formatter(format: String): SimpleDateFormat {
        return SimpleDateFormat(format, Locale.getDefault())
    }
}