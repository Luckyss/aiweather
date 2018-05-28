package ru.androidpirates.aiweather.injection.global

import ru.androidpiraters.aiweather.injection.WeatherComponent
import ru.androidpirates.aiweather.injection.main.MainComponent

interface ComponentsCreator :
        MainComponent.Creator,
        WeatherComponent.Creator