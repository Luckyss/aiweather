package ru.androidpirates.aiweather.common

import android.content.Context
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HostSelectionInterceptor(private val appContext: Context) : Interceptor {
    @Volatile private var url = HttpUrl.parse(HostPreferences.getHost(appContext))

    var urlString: String
        get() = url!!.toString()
        set(url) {
            this.url = HttpUrl.parse(url)
            HostPreferences.setHost(appContext, url)
        }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val requestedUrl = request.url()

        url?.run {
            requestedUrl.newBuilder(urlString)?.run {
                addEncodedPathSegments(requestedUrl.encodedPath().removePrefix("/"))
                query(requestedUrl.query())
                port(requestedUrl.port())
                scheme(requestedUrl.scheme())

                val newUrl = build()

                request = request.newBuilder()
                        .url(newUrl)
                        .build()
            }
        }
        return chain.proceed(request)
    }
}