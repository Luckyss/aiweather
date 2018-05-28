package ru.androidpirates.aiweather.presentation.providers

import android.content.Context
import android.support.annotation.StringRes
import ru.androidpirates.aiweather.injection.qualifiers.ForApplication
import javax.inject.Inject

/**
 * Provides access to string resources to classes that have no access to Context (publishers, mappers, etc.)
 */
class StringProvider @Inject constructor(@param:ForApplication private val context: Context) {

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }
}
