package ru.androidpirates.aiweather.presentation.mvp

class WrongViewTypeException @JvmOverloads constructor(
        message: String? = null,
        cause: Throwable? = null,
        enableSuppression: Boolean = true,
        writableStackTrace: Boolean = true
) : Exception(message, cause, enableSuppression, writableStackTrace)