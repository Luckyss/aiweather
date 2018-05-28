package ru.androidpiraters.aiweather.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.androidpiraters.aiweather.data.db.weather.WeatherInfoDao
import ru.androidpiraters.aiweather.data.db.weather.WeatherInfoEntity

// TODO: implement database
@Database(entities = [WeatherInfoEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherInfoDao(): WeatherInfoDao
}