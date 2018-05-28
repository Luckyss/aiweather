package ru.androidpiraters.aiweather.data.db.weather

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import ru.androidpiraters.aiweather.data.db.weather.WeatherInfoEntity.Companion.TABLE_NAME


// TODO: implement database entities
@Entity(tableName = TABLE_NAME)
class WeatherInfoEntity(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        val name: String

) {
    companion object {
        const val TABLE_NAME: String = "weather"
    }
}