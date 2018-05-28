package ru.androidpirates.aiweather.injection

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import okhttp3.Interceptor
import ru.androidpirates.aiweather.injection.modules.NetworkModule.AppInterceptor
import ru.androidpirates.aiweather.injection.modules.NetworkModule.NetworkInterceptor
import ru.androidpirates.aiweather.injection.qualifiers.ForApplication
import java.util.*
import javax.inject.Singleton

@Module
class InstrumentationModule {

    @Provides
    @Singleton
    @NetworkInterceptor
    @ElementsIntoSet
    fun provideNetworkInterceptors(): Set<Interceptor> {
        return Collections.emptySet()
    }

    @Provides
    @Singleton
    @AppInterceptor
    fun provideAppInterceptors(@ForApplication context: Context): Interceptor {
        return ChuckInterceptor(context)
    }
}