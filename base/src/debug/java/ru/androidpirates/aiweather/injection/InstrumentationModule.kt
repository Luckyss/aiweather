package ru.androidpirates.aiweather.injection

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import ru.androidpirates.aiweather.injection.modules.NetworkModule.AppInterceptor
import ru.androidpirates.aiweather.injection.modules.NetworkModule.NetworkInterceptor
import ru.androidpirates.aiweather.injection.qualifiers.ForApplication
import timber.log.Timber
import javax.inject.Singleton


@Module
class InstrumentationModule {

    @Provides
    @NetworkInterceptor
    @IntoSet
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor { Timber.d("OkHttp: %s", it) }
                .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @NetworkInterceptor
    @IntoSet
    @Singleton
    fun provideStethoInterceptor(): Interceptor = StethoInterceptor()

    @Provides
    @AppInterceptor
    @Singleton
    fun provideChuckInterceptor(@ForApplication context: Context): Interceptor {
        return ChuckInterceptor(context)
    }
}