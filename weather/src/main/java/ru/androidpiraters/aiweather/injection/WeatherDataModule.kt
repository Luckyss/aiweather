package ru.androidpiraters.aiweather.injection

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.androidpiraters.aiweather.data.WeatherApi
import ru.androidpiraters.aiweather.data.db.WeatherDatabase
import ru.androidpirates.aiweather.injection.qualifiers.ForApplication
import javax.inject.Singleton

@Module
class WeatherDataModule {

    @Provides
    @Singleton
    fun provideWeatherDataBase(@ForApplication context: Context): WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, "WeatherDatabase").build()
    }

    @Provides
    @Singleton
    fun provideClientDeliveryApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
//        return WeatherApiMock()
    }
}