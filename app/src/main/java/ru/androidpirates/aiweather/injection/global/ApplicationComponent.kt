package ru.androidpirates.aiweather.injection.global

import dagger.BindsInstance
import dagger.Component
import ru.androidpirates.aiweather.AiWeatherApp
import ru.androidpirates.aiweather.injection.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DataModule::class])
interface ApplicationComponent : ComponentsCreator {

    fun inject(app: AiWeatherApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: AiWeatherApp): Builder

        fun build(): ApplicationComponent
    }
}