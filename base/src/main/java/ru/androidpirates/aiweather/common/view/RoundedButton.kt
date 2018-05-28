package ru.androidpirates.aiweather.common.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.annotation.IntDef
import android.support.annotation.RequiresApi
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import ru.androidpirates.aiweather.base.R
import ru.androidpirates.aiweather.common.extensions.dpToPx
import ru.androidpirates.aiweather.common.extensions.getColorCompat
import ru.androidpirates.aiweather.common.extensions.getDrawableCompat

class RoundedButton @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = R.style.Button_Accent
) : AppCompatButton(context, attrs, defStyleAttr) {

    init {
        init(attrs)
    }

    @IntDef(TRANSPARENT, ACCENT)
    @Retention(AnnotationRetention.RUNTIME)
    private annotation class ColorScheme

    @ColorScheme var type: Int = TRANSPARENT
        set(type) {
            field = type
            initBg()
        }

    private fun init(attrs: AttributeSet?) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.RoundedButton)
        try {
            type = array.getInt(R.styleable.RoundedButton_rb_type, TRANSPARENT)
        } finally {
            array.recycle()
        }
        initBg()
        initTextColor()
        gravity = Gravity.CENTER
        setAllCaps(false)
        minHeight = context.dpToPx(36f)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
    }

    private fun initBg() {
        initRippleBackground()
    }

    private fun initTextColor() {
        setTextColor(ColorStateList(
                arrayOf(
                        intArrayOf(-android.R.attr.state_enabled),
                        intArrayOf()
                ),
                intArrayOf(
                        context.getColorCompat(android.R.color.tertiary_text_light),
                        context.getColorCompat(android.R.color.primary_text_light)
                )
        ))
    }

    private fun initRippleBackground() {
        when (type) {
            TRANSPARENT -> setBackgroundResource(R.drawable.rounded_button_ripple_states)
            ACCENT      -> setBackgroundResource(R.drawable.rounded_button_ripple_states_accent)
        }
    }

    companion object {
        const val TRANSPARENT = 0
        const val ACCENT = 1
    }
}