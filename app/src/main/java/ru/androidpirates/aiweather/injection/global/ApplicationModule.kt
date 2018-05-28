package ru.androidpirates.aiweather.injection.global

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.androidpirates.aiweather.AiWeatherApp
import ru.androidpirates.aiweather.injection.qualifiers.ForApplication

@Module
class ApplicationModule {

    @ForApplication
    @Provides
    fun provideApplicationContext(app: AiWeatherApp): Context {
        return app.applicationContext
    }
}