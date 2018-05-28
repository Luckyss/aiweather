package ru.androidpirates.aiweather.common.extensions

import android.os.Bundle
import android.support.v4.app.Fragment

fun <T : Fragment> T.withArguments(
        clear: Boolean = false,
        action: Bundle.() -> Unit
): T {
    val args = arguments ?: Bundle()
    if (clear) {
        args.clear()
    }
    action(args)
    arguments = args
    return this
}