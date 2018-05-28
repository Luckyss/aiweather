package ru.androidpirates.aiweather

import android.app.Application
import ru.androidpirates.aiweather.injection.global.ApplicationComponent
import ru.androidpirates.aiweather.injection.global.ComponentsCreator
import ru.androidpirates.aiweather.injection.global.DaggerApplicationComponent
import ru.androidpirates.aiweather.network.StethoHelper
import timber.log.Timber

class AiWeatherApp : Application(), ComponentsCreator {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        StethoHelper.initializeWithDefaults(this)
    }

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
    }

    override fun createMainComponent() = component.createMainComponent()
    override fun createWeatherComponent() = component.createWeatherComponent()
}