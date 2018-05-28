package ru.androidpiraters.aiweather.injection.mock

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Query
import ru.androidpiraters.aiweather.data.WeatherApi
import ru.androidpiraters.aiweather.data.WeatherException
import ru.androidpiraters.aiweather.data.model.weather.Clouds
import ru.androidpiraters.aiweather.data.model.weather.Coord
import ru.androidpiraters.aiweather.data.model.weather.Main
import ru.androidpiraters.aiweather.data.model.weather.Sys
import ru.androidpiraters.aiweather.data.model.weather.WeatherItem
import ru.androidpiraters.aiweather.data.model.weather.WeatherResponseRaw
import ru.androidpiraters.aiweather.data.model.weather.Wind
import ru.androidpirates.aiweather.common.mock.RandomString
import java.util.*
import java.util.concurrent.TimeUnit

class WeatherApiMock : WeatherApi {

    override fun getCurrentWeatherInfo(
            @Query("q") cityName: String,
            @Query("APPID") appId: String,
            @Query("units") units: String
    ): Single<WeatherResponseRaw> {
        val random = RandomString()
        return Single.create<WeatherResponseRaw> {
            Completable.complete().delay(1, TimeUnit.SECONDS).subscribe {
                if (Random().nextBoolean()) {
                    it.onSuccess(WeatherResponseRaw(
                            dt = 0,
                            coord = Coord(0.0, 0.0),
                            visibility = 0,
                            weather = arrayListOf(WeatherItem()),
                            name = random.nextString(),
                            cod = 200,
                            main = Main(),
                            clouds = Clouds(),
                            id = 0,
                            sys = Sys(),
                            base = random.nextString(),
                            wind = Wind()
                    ))
                } else {
                    it.onError(WeatherException("Test Server Exception"))
                }
            }
        }

    }
}