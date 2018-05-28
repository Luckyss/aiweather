package ru.androidpiraters.aiweather.data

class WeatherException @JvmOverloads constructor(reason: String, cause: Throwable? = null) : Exception(reason, cause)