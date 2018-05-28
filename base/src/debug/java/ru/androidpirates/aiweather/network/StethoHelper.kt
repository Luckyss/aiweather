package ru.androidpirates.aiweather.network

import android.content.Context
import com.facebook.stetho.Stetho

object StethoHelper {
    @JvmStatic fun initializeWithDefaults(context: Context) {
        Stetho.initializeWithDefaults(context)
    }
}
 