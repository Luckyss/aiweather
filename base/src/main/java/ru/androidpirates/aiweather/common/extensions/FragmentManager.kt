package ru.androidpirates.aiweather.common.extensions

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

inline fun FragmentManager?.transaction(
        commit: FragmentTransaction.() -> Unit = { commit() },
        action: FragmentTransaction.() -> Unit
) {
    if (this != null) {
        val transaction = beginTransaction()
        action(transaction)
        commit(transaction)
    }
}