package ru.androidpiraters.aiweather.data.db.weather

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import io.reactivex.Single
import ru.androidpirates.aiweather.data.db.BaseDao.Companion.DELETE_FROM
import ru.androidpirates.aiweather.data.db.BaseDao.Companion.SELECT_ALL_FROM
import ru.androidpirates.aiweather.data.db.BaseDao.Companion.WHERE

@Dao
abstract class WeatherInfoDao {

    @Query(SELECT_ALL_FROM + WeatherInfoEntity.TABLE_NAME)
    abstract fun getAll(): Single<List<WeatherInfoEntity>>

    @Query(SELECT_ALL_FROM + WeatherInfoEntity.TABLE_NAME + WHERE + " id = :key")
    abstract fun getSingular(key: String): Single<WeatherInfoEntity>

    @Transaction
    open fun replace(weatherInfoEntity: WeatherInfoEntity) {
        clearTable()
        insert(weatherInfoEntity)
    }

    @Insert abstract fun insertAll(messages: List<WeatherInfoEntity>)

    @Insert abstract fun insert(vararg message: WeatherInfoEntity)

    @Delete abstract fun delete(message: WeatherInfoEntity)

    @Query(DELETE_FROM + WeatherInfoEntity.TABLE_NAME) abstract fun clearTable()
}