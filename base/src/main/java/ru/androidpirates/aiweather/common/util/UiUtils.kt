package ru.androidpirates.aiweather.common.util

import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.support.annotation.ColorInt


object UiUtils {

    fun dpToPx(value: Float): Int {
        val density = Resources.getSystem().displayMetrics.density
        return if (value != 0f)
            Math.ceil((density * value).toDouble()).toInt()
        else 0
    }

    fun getSelectableBackground(@ColorInt colorSelected: Int, @ColorInt colorNormal: Int): StateListDrawable {
        val states = StateListDrawable()

        val clrActive = ColorDrawable(adjustAlpha(colorSelected, 0x3A))

        states.addState(intArrayOf(android.R.attr.state_selected), clrActive)
        states.addState(intArrayOf(), ColorDrawable(colorNormal))

        states.setEnterFadeDuration(200)
        states.setExitFadeDuration(200)

        return states
    }

    fun adjustAlpha(color: Int, alpha: Int): Int {
        return alpha shl 24 or (color and 0x00ffffff)
    }
}
