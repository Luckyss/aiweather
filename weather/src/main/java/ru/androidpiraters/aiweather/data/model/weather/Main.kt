package ru.androidpiraters.aiweather.data.model.weather

import com.google.gson.annotations.SerializedName

data class Main(
	val temp: Double? = null,
	@SerializedName("temp_min") val tempMin: Double? = null,
	val humidity: Double? = null,
	val pressure: Double? = null,
    @SerializedName("temp_max") val tempMax: Double? = null
)
