package ru.androidpiraters.aiweather.presentation

import ru.androidpiraters.aiweather.domain.RequestWeather
import ru.androidpiraters.aiweather.presentation.model.WeatherInfoDisplayItem
import ru.androidpirates.aiweather.injection.scope.ConfigPersistent
import ru.androidpirates.aiweather.presentation.mvp.BasePresenter
import javax.inject.Inject

@ConfigPersistent
class WeatherPresenter @Inject constructor(
        private var weatherDisplayableMapper: WeatherInfoDisplayItem.MapperToWeatherInfoDisplayItem,
        private var requestWeather: RequestWeather
) : BasePresenter<WeatherContract.View>(), WeatherContract.Presenter {

    override fun getWeatherInfo(location: String) {
        requestWeather.getSingle(location)
                .processLoading()
                .subscribe({
                    val data = weatherDisplayableMapper.apply(it)
                    getView().onWeatherDataLoaded(data)
                }, {
                    getView().showError(it)
                })
    }

}