package ru.androidpiraters.aiweather.data.model.weather

data class WeatherInfo(
        val dt: Int,
        val coord: Coord,
        val visibility: Int,
        val weather: List<WeatherItem?>,
        val name: String,
        val cod: Int,
        val main: Main,
        val clouds: Clouds,
        val id: Int,
        val sys: Sys,
        val base: String,
        val wind: Wind
) {

    private constructor(builder: Builder) : this(
            builder.dt,
            builder.coord,
            builder.visibility,
            builder.weather,
            builder.name,
            builder.cod,
            builder.main,
            builder.clouds,
            builder.id,
            builder.sys,
            builder.base,
            builder.wind
    )


    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        internal var dt: Int = 0
        internal var coord: Coord = Coord()
        internal var visibility: Int = 0
        internal var weather: List<WeatherItem?> = ArrayList()
        internal var name: String = ""
        internal var cod: Int = 0
        internal var main: Main = Main()
        internal var clouds: Clouds = Clouds()
        internal var id: Int = 0
        internal var sys: Sys = Sys()
        internal var base: String = ""
        internal var wind: Wind = Wind()

        fun dt(dt: Int): Builder {
            this.dt = dt
            return this
        }

        fun coord(coord: Coord): Builder {
            this.coord = coord
            return this
        }

        fun visibility(visibility: Int): Builder {
            this.visibility = visibility
            return this
        }

        fun weather(weather: List<WeatherItem?>): Builder {
            this.weather = weather
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun cod(cod: Int): Builder {
            this.cod = cod
            return this
        }

        fun main(main: Main): Builder {
            this.main = main
            return this
        }

        fun clouds(clouds: Clouds): Builder {
            this.clouds = clouds
            return this
        }

        fun id(id: Int): Builder {
            this.id = id
            return this
        }

        fun sys(sys: Sys): Builder {
            this.sys = sys
            return this
        }

        fun base(base: String): Builder {
            this.base = base
            return this
        }

        fun wind(wind: Wind): Builder {
            this.wind = wind
            return this
        }

        fun build() = WeatherInfo(this)
    }

}