package ru.androidpirates.aiweather.common.view

import android.content.Context
import android.support.annotation.StringRes
import android.support.transition.Fade
import android.support.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.view_rounded_progress_button.view.rounded_progress_button_button
import kotlinx.android.synthetic.main.view_rounded_progress_button.view.rounded_progress_button_progressBar
import ru.androidpirates.aiweather.base.R

class RoundedProgressButton @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        init(attrs)
    }

    private lateinit var button: RoundedButton
    private lateinit var progressBar: ProgressBar

    var isLoading: Boolean = false
        set(isLoading) {
            if (field != isLoading) {
                field = isLoading
                TransitionManager.beginDelayedTransition(this, Fade().setDuration(200))
                button.visibility = if (field) INVISIBLE else VISIBLE
                progressBar.visibility = if (field) VISIBLE else INVISIBLE
            }
        }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_rounded_progress_button, this)
        button = rounded_progress_button_button
        progressBar = rounded_progress_button_progressBar

        val a = context.obtainStyledAttributes(attrs, R.styleable.RoundedProgressButton)
        try {
            button.text = a.getString(R.styleable.RoundedProgressButton_rpb_text)
            if (a.hasValue(R.styleable.RoundedProgressButton_rpb_textColor)) {
                button.setTextColor(
                        a.getColorStateList(R.styleable.RoundedProgressButton_rpb_textColor)
                )
            }
            button.type = a.getInt(R.styleable.RoundedProgressButton_rpb_type, button.type)
        } finally {
            a.recycle()
        }
    }

    fun setText(text: CharSequence) {
        button.text = text
    }

    fun setText(text: CharSequence, type: TextView.BufferType) {
        button.setText(text, type)
    }

    fun setText(text: CharArray, start: Int, len: Int) {
        button.setText(text, start, len)
    }

    fun setText(@StringRes resId: Int) {
        button.setText(resId)
    }

    fun setText(@StringRes resId: Int, type: TextView.BufferType) {
        button.setText(resId, type)
    }

    override fun setOnClickListener(l: View.OnClickListener?) {
        button.setOnClickListener(l)
    }

    override fun setEnabled(enabled: Boolean) {
        button.isEnabled = enabled
    }
}
