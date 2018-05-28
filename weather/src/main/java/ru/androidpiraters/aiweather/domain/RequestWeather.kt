package ru.androidpiraters.aiweather.domain

import io.reactivex.Single
import ru.androidpiraters.aiweather.data.model.weather.WeatherInfo
import ru.androidpiraters.aiweather.data.repository.WeatherRepository
import ru.androidpirates.aiweather.domain.ReactiveInteractor

import javax.inject.Inject

class RequestWeather @Inject constructor(
        private val weatherRepository: WeatherRepository
) : ReactiveInteractor.RequestInteractor<String, WeatherInfo> {

    override fun getSingle(params: String): Single<WeatherInfo> {
        return weatherRepository.getCurrentWeatherInfo(params)
    }

}