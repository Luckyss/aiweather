package ru.androidpirates.aiweather.common.extensions

import android.os.Handler
import android.os.Looper

fun Any.customTag(): String {
    return "weather_${
    if (javaClass.simpleName.length < 12)
        javaClass.simpleName
    else
        javaClass.simpleName.subSequence(0..12)
    }"
}

fun Any.runOnMain(block: () -> Unit) {
    runOnMain(Runnable { block() })
}

fun Any.runOnMain(runnable: Runnable) {
    Handler(Looper.getMainLooper()).post(runnable)
}