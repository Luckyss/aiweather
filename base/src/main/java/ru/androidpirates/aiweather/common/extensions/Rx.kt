package ru.androidpirates.aiweather.common.extensions

import io.reactivex.disposables.Disposable

fun Disposable?.disposeSafely() {
    if (this != null && !isDisposed) {
        dispose()
    }
}