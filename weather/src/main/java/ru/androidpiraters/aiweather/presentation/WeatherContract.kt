package ru.androidpiraters.aiweather.presentation

import ru.androidpiraters.aiweather.presentation.model.WeatherInfoDisplayItem
import ru.androidpirates.aiweather.presentation.mvp.BaseContract

interface WeatherContract {

    interface View : BaseContract.View {
        fun onWeatherDataLoaded(data: WeatherInfoDisplayItem)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getWeatherInfo(location: String)
    }
}