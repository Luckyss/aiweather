package ru.androidpirates.aiweather.injection.global

import dagger.Module
import ru.androidpiraters.aiweather.injection.WeatherDataModule

@Module(includes = [
    WeatherDataModule::class
])
class DataModule