package ru.androidpiraters.aiweather.injection

import dagger.Subcomponent
import ru.androidpiraters.aiweather.presentation.WeatherFragment
import ru.androidpirates.aiweather.injection.scope.ConfigPersistent

@ConfigPersistent
@Subcomponent(modules = [WeatherModule::class])
interface WeatherComponent {

    fun inject(weatherFragment: WeatherFragment)

    interface Creator {
        
        fun createWeatherComponent(): WeatherComponent
    }
}