package ru.androidpirates.aiweather.common

import android.content.Context
import androidx.core.content.edit
import ru.androidpirates.aiweather.common.constants.Constants

class HostPreferences(context: Context) {
    private val mPreferences = context.getSharedPreferences(PREFERENCE_HOSTS, Context.MODE_PRIVATE)

    var host: String
        get() = mPreferences.getString(PREFERENCE_CURRENT_HOST, Constants.BASE_API_URL)
        set(host) = mPreferences.edit { putString(PREFERENCE_CURRENT_HOST, host) }

    companion object {
        private const val PREFERENCE_HOSTS = "preference_hosts"
        private const val PREFERENCE_CURRENT_HOST = "preference_current_host"

        @JvmStatic fun getHost(context: Context): String {
            return HostPreferences(context).host
        }

        @JvmStatic fun setHost(context: Context, host: String) {
            HostPreferences(context).host = host
        }
    }
}