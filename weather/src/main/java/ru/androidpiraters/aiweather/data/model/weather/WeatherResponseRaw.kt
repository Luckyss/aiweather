package ru.androidpiraters.aiweather.data.model.weather

import ru.androidpirates.aiweather.data.EssentialMapper
import javax.inject.Inject

data class WeatherResponseRaw(
        val dt: Int?,
        val coord: Coord?,
        val visibility: Int?,
        val weather: List<WeatherItem?>?,
        val name: String?,
        val cod: Int?,
        val main: Main?,
        val clouds: Clouds?,
        val id: Int?,
        val sys: Sys?,
        val base: String?,
        val wind: Wind?
) {
    class MapperToWeatherInfo @Inject constructor() : EssentialMapper<WeatherResponseRaw, WeatherInfo> {
        override fun applyMap(raw: WeatherResponseRaw): WeatherInfo {
            return WeatherInfo.Builder()
                    .dt(raw.dt ?: 0)
                    .coord(raw.coord ?: Coord(0.0, 0.0))
                    .visibility(raw.visibility ?: 0)
                    .weather(raw.weather ?: arrayListOf(WeatherItem("", "", "", 0)))
                    .name(raw.name ?: "")
                    .cod(raw.cod ?: 0)
                    .main(raw.main ?: Main())
                    .clouds(raw.clouds ?: Clouds())
                    .id(raw.id ?: 0)
                    .sys(raw.sys ?: Sys())
                    .base(raw.base ?: "")
                    .wind(raw.wind ?: Wind())
                    .build()
        }

        override fun getMissedParams(raw: WeatherResponseRaw): List<String>? {
            val missedParams = ArrayList<String>()

            raw.dt ?: missedParams.add("dt")
            raw.coord ?: missedParams.add("coords")
//            raw.visibility ?: missedParams.add("visibility")
            raw.weather ?: missedParams.add("weather")
            raw.name ?: missedParams.add("name")
            raw.cod ?: missedParams.add("cod")
            raw.main ?: missedParams.add("main")
            raw.clouds ?: missedParams.add("clouds")
            raw.id ?: missedParams.add("id")
            raw.sys ?: missedParams.add("sys")
            raw.base ?: missedParams.add("base")
            raw.wind ?: missedParams.add("wind")

            return missedParams
        }
    }
}
