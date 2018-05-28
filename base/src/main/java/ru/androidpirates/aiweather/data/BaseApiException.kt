package ru.androidpirates.aiweather.data

class BaseApiException(val code: Int?, message: String?) : RuntimeException("BaseApi error: { " +
        "code = ${code.toString()}, message = \"${message.toString()}\" }")
 