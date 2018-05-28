package ru.androidpiraters.aiweather.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.androidpiraters.aiweather.data.model.weather.WeatherResponseRaw
import ru.androidpirates.aiweather.weather.BuildConfig

interface WeatherApi {

    companion object {
        const val PREFIX = "/data/"
        const val API_VERSION = "2.5"
    }

    @GET("$PREFIX$API_VERSION/weather")
    fun getCurrentWeatherInfo(
            @Query("q") cityName: String,
            @Query("APPID") appId: String = BuildConfig.WEATHER_API_KEY,
            @Query("units") units: String = "metric"
    ): Single<WeatherResponseRaw>

}