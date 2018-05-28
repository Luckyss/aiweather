package ru.androidpirates.aiweather.common.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import ru.androidpirates.aiweather.base.R

class LockableViewPager @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : ViewPager(context, attrs) {
    var swipeLocked: Boolean

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.LockableViewPager)
        try {
            swipeLocked = array.getBoolean(R.styleable.LockableViewPager_lvp_locked, false)
        } finally {
            array.recycle()
        }
    }

    /**
     * Set current item without animation
     */
    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return !swipeLocked && super.onInterceptTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return !swipeLocked && super.onTouchEvent(event)
    }
}