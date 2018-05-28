package ru.androidpiraters.aiweather.presentation


import ai.api.android.AIConfiguration
import ai.api.android.AIService
import ai.api.model.AIError
import ai.api.model.AIResponse
import ai.api.ui.AIButton
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_weather.aiButton
import kotlinx.android.synthetic.main.fragment_weather.cityName
import kotlinx.android.synthetic.main.fragment_weather.humidity
import kotlinx.android.synthetic.main.fragment_weather.minMaxTemp
import kotlinx.android.synthetic.main.fragment_weather.noData
import kotlinx.android.synthetic.main.fragment_weather.pressure
import kotlinx.android.synthetic.main.fragment_weather.swipeRefresh
import kotlinx.android.synthetic.main.fragment_weather.temp
import kotlinx.android.synthetic.main.fragment_weather.weatherDesc
import kotlinx.android.synthetic.main.fragment_weather.weatherIcon
import kotlinx.android.synthetic.main.fragment_weather.weatherLayout
import kotlinx.android.synthetic.main.fragment_weather.windSpeed
import ru.androidpiraters.aiweather.data.TextToSpeechHelper
import ru.androidpiraters.aiweather.injection.WeatherComponent
import ru.androidpiraters.aiweather.presentation.model.WeatherInfoDisplayItem
import ru.androidpirates.aiweather.common.extensions.customTag
import ru.androidpirates.aiweather.presentation.base.BaseInjectingFragment
import ru.androidpirates.aiweather.weather.BuildConfig
import ru.androidpirates.aiweather.weather.R
import ru.androidpirates.permissions.retriever.PermissionRetriever

class WeatherFragment : BaseInjectingFragment<WeatherContract.View,
        WeatherPresenter>(), WeatherContract.View, AIButton.AIButtonListener {

    companion object {
        val TAG: String = WeatherFragment.customTag()
    }

    private val permissionRetriever = PermissionRetriever()
    internal lateinit var aiService: AIService
    internal lateinit var currentLocation: String

    override fun onInject() {
        (application as? WeatherComponent.Creator)
                ?.createWeatherComponent()
                ?.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_weather

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        permissionRetriever.withPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                .withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                .withPermission(android.Manifest.permission.RECORD_AUDIO)
                .run(this, {
                    initAiButton()
                    TextToSpeechHelper.init(context)
                }, {

                })
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        permissionRetriever.onPermissionResult(requestCode)
    }

    override fun onResume() {
        super.onResume()

        aiButton.resume()

        swipeRefresh.setOnRefreshListener { presenter.getWeatherInfo(currentLocation) }
    }

    override fun onPause() {
        super.onPause()

        aiButton.pause()
        swipeRefresh.setOnRefreshListener(null)
    }

    override fun showProgress() {
        super.showProgress()

        swipeRefresh.isRefreshing = true
    }

    override fun hideProgress() {
        super.hideProgress()

        swipeRefresh.isRefreshing = false
    }

    override fun onWeatherDataLoaded(data: WeatherInfoDisplayItem) {
        noData.visibility = GONE
        weatherLayout.visibility = VISIBLE

//        coord.text = getString(R.string.coords, data.coord)
//        visibility.text = data.visibility
        Glide.with(this)
                .load(data.weatherIcon)
                .into(weatherIcon)
        weatherDesc.text = data.weatherDesc
        cityName.text = getString(R.string.template_underline_text, data.cityName)
        temp.text = getString(R.string.template_degrees, data.temp)
        minMaxTemp.text = getString(R.string.template_min_max, data.minTemp, data.maxTemp)
        humidity.text = data.humidity
        pressure.text = getString(R.string.template_pressure, data.pressure)
        windSpeed.text = getString(R.string.template_wind_speed, data.windSpeed)

        TextToSpeechHelper.speak(data.speech)
    }

    private fun initAiButton() {
        val config = AIConfiguration(BuildConfig.AI_ACCESS_TOKEN,
                ai.api.AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System)

        config.recognizerStartSound = resources.openRawResourceFd(R.raw.test_start)
        config.recognizerStopSound = resources.openRawResourceFd(R.raw.test_stop)
        config.recognizerCancelSound = resources.openRawResourceFd(R.raw.test_cancel)

        aiButton.initialize(config)
        aiButton.setResultsListener(this)

        aiService = AIService.getService(context, config)
    }

    // --------------------------------------------------------------------------------------------
    //      AIButtonListener METHODS IMPLEMENTATION
    // --------------------------------------------------------------------------------------------

    override fun onResult(response: AIResponse) {
//            resultTextView.setText(response.result.fulfillment.speech)

        // this is example how to get different parts of result object
        val status = response.status

        val result = response.result
        val speech = result.fulfillment.speech

        val metadata = result.metadata
        if (metadata != null) {
            Log.i(TAG, "Intent id: " + metadata.intentId)
            Log.i(TAG, "Intent name: " + metadata.intentName)
        }

        val params = result.parameters
        if (params != null && !params.isEmpty()) {
            Log.i(TAG, "Parameters: ")
            for ((key, value) in params) {
                if (key == "location") {
                    var locationValue  = value.asJsonObject.keySet().toList()[0]
                    currentLocation = value.asJsonObject.get(locationValue).asString
                    presenter.getWeatherInfo(currentLocation)
                    return
                }
                Log.i(TAG, String.format("%s: %s", key, value.toString()))
            }
        }
    }

    override fun onError(error: AIError) {
        Log.e(TAG, "onError")
    }

    override fun onCancelled() {
        Log.e(TAG, "onCancelled")
    }
}