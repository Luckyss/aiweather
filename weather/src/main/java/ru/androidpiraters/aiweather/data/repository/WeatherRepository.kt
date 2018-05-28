package ru.androidpiraters.aiweather.data.repository

import io.reactivex.Single
import ru.androidpiraters.aiweather.data.WeatherApi
import ru.androidpiraters.aiweather.data.db.WeatherDatabase
import ru.androidpiraters.aiweather.data.model.weather.WeatherInfo
import ru.androidpiraters.aiweather.data.model.weather.WeatherResponseRaw
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
        private val weatherApi: WeatherApi,
        private val weatherInfoMapper: WeatherResponseRaw.MapperToWeatherInfo,
        private val database: WeatherDatabase
) {

    fun getCurrentWeatherInfo(cityName: String): Single<WeatherInfo> {
        return weatherApi.getCurrentWeatherInfo(cityName)
                .map(weatherInfoMapper)
    }
}