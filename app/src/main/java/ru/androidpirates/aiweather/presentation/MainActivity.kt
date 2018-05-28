package ru.androidpirates.aiweather.presentation


import ru.androidpirates.aiweather.R
import ru.androidpirates.aiweather.injection.main.MainComponent
import ru.androidpirates.aiweather.presentation.base.BaseInjectingActivity


open class MainActivity : BaseInjectingActivity<MainComponent>() {

    override fun createComponent(): MainComponent {
        return (application as MainComponent.Creator).createMainComponent()
    }

    override fun getLayoutId(): Int = R.layout.base_activity
}