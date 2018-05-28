package ru.androidpirates.aiweather.presentation.base

import android.content.Intent
import android.os.Bundle

abstract class BaseInjectingActivity<Component : Any> : BaseActivity() {

    val component: Component by lazy { createComponent() }

    override fun onCreate(savedInstanceState: Bundle?) {
        onInject(component)
        super.onCreate(savedInstanceState)
    }

    protected open fun onInject(component: Component) {}

    protected abstract fun createComponent(): Component

    protected fun start(action: String) {
        startActivity(Intent(action))
        //activity?.overridePendingTransition()
    }
}