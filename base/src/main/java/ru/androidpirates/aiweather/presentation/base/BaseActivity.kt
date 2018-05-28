package ru.androidpirates.aiweather.presentation.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import ru.androidpirates.aiweather.base.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(getLayoutId())
        val toolbar = findViewById<Toolbar>(getToolbarId())
        if (toolbar != null) setSupportActionBar(toolbar)
    }

    @IdRes protected open fun getToolbarId(): Int {
        return R.id.toolbar
    }

    @LayoutRes protected abstract fun getLayoutId(): Int
}