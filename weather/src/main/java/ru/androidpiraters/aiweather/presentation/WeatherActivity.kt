package ru.androidpiraters.aiweather.presentation

import android.os.Bundle
import ru.androidpirates.aiweather.common.extensions.transaction
import ru.androidpiraters.aiweather.injection.WeatherComponent
import ru.androidpirates.aiweather.base.R
import ru.androidpirates.aiweather.common.extensions.customTag
import ru.androidpirates.aiweather.presentation.base.BaseInjectingActivity

class WeatherActivity : BaseInjectingActivity<WeatherComponent>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val fragment = WeatherFragment()
            supportFragmentManager.transaction {
                replace(R.id.main_content, fragment, fragment.customTag())
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.base_activity

    override fun createComponent(): WeatherComponent {
        return (application as WeatherComponent.Creator).createWeatherComponent()
    }
}
