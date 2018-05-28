package ru.androidpiraters.aiweather.presentation.model

import ru.androidpiraters.aiweather.data.model.weather.WeatherInfo
import ru.androidpirates.aiweather.data.EssentialMapper
import javax.inject.Inject
import kotlin.math.roundToInt

data class WeatherInfoDisplayItem(
        val coord: String,
        val visibility: String,
        val weatherIcon: String,
        val weatherDesc: String,
        val cityName: String,
        val temp: String,
        val minTemp: String,
        val maxTemp: String,
        val humidity: String,
        val pressure: String,
        val windSpeed: String,
        val speech: String
) {
    class MapperToWeatherInfoDisplayItem @Inject constructor()
        : EssentialMapper<WeatherInfo, WeatherInfoDisplayItem> {

        override fun applyMap(raw: WeatherInfo): WeatherInfoDisplayItem {
            val temperature = raw.main.temp?.roundToInt().toString()
            val weatherDescription = raw.weather[0]?.description?.capitalize() ?: ""

            return WeatherInfoDisplayItem(
                    coord = "${raw.coord.lat}, ${raw.coord.lon}",
                    visibility = "${raw.visibility}m",
                    weatherIcon = "http://openweathermap.org/img/w/${(raw.weather[0]?.icon
                            ?: "")}.png",
                    weatherDesc = weatherDescription,
                    cityName = raw.name,
                    temp = temperature,
                    minTemp = raw.main.tempMin.toString(),
                    maxTemp = raw.main.tempMax.toString(),
                    humidity = "${raw.main.humidity?.roundToInt().toString()}%",
                    pressure = raw.main.pressure?.roundToInt().toString(),
                    windSpeed = raw.wind.speed?.toString() ?: "",

                    // TODO: move to resources
                    speech = "The weather today. Location: ${raw.name}. Temperature: $temperature."
                            + "$weatherDescription. Have a good day!")
        }

        override fun getMissedParams(raw: WeatherInfo): List<String>? {
            val missedParams = ArrayList<String>()

            // TODO: we can enable this if we need to know what params are missing
//        raw.coord.lon ?: missedParams.add("coords.lon")
//        raw.coord.lat ?: missedParams.add("coords.lat")
//        raw.weather[0]?.icon ?: missedParams.add("weather.icon")
//        raw.weather[0]?.description ?: missedParams.add("weather.desc")
//        raw.main.temp ?: missedParams.add("temp")
//        raw.main.tempMin ?: missedParams.add("tempMin")
//        raw.main.tempMax ?: missedParams.add("tempMax")
//        raw.main.humidity ?: missedParams.add("humidity")
//        raw.main.pressure ?: missedParams.add("pressure")
//        raw.wind.speed ?: missedParams.add("wind.speed")

            return missedParams
        }
    }
}