package ru.androidpiraters.aiweather.injection

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import ru.androidpiraters.aiweather.presentation.WeatherItemComparator
import ru.androidpiraters.aiweather.presentation.WeatherViewHolder
import ru.androidpirates.aiweather.common.preconditions.AndroidPreconditions
import ru.androidpirates.aiweather.presentation.recyclerview.ItemComparator
import ru.androidpirates.aiweather.presentation.recyclerview.RecyclerViewAdapter
import ru.androidpirates.aiweather.presentation.recyclerview.ViewHolderBinder
import ru.androidpirates.aiweather.presentation.recyclerview.ViewHolderFactory
import ru.androidpirates.aiweather.presentation.recyclerview.ViewHolderTypes

@Module
abstract class WeatherModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideRecyclerViewAdapter(
                itemComparator: ItemComparator,
                factoryMap: @JvmSuppressWildcards Map<Int, ViewHolderFactory>,
                binderMap: @JvmSuppressWildcards Map<Int, ViewHolderBinder>,
                androidPreconditions: AndroidPreconditions
        ): RecyclerViewAdapter {
            return RecyclerViewAdapter(itemComparator, factoryMap, binderMap, androidPreconditions)
        }

        @JvmStatic
        @Provides
        fun provideComparator(): ItemComparator = WeatherItemComparator()
    }

    @Binds
    @IntoMap
    @IntKey(ViewHolderTypes.WEATHER_INFO)
    abstract fun provideWeatherViewHolderFactory(
            factory: WeatherViewHolder.Factory
    ): ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(ViewHolderTypes.WEATHER_INFO)
    abstract fun provideWeatherViewHolderBinder(
            binder: WeatherViewHolder.Binder
    ): ViewHolderBinder
}
