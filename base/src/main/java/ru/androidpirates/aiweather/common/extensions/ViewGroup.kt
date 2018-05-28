package ru.androidpirates.aiweather.common.extensions

import android.view.ViewGroup
import androidx.core.view.forEach

fun ViewGroup.setChildrenEnabled(enabled: Boolean) {
    forEach {
        it.isEnabled = enabled
        if (it is ViewGroup) {
            it.setChildrenEnabled(enabled)
        }
    }
}