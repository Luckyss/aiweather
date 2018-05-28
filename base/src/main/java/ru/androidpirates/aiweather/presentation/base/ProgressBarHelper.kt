package ru.androidpirates.aiweather.presentation.base

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.widget.toast
import ru.androidpirates.aiweather.base.BuildConfig
import ru.androidpirates.aiweather.base.R
import ru.androidpirates.aiweather.common.extensions.getColorCompat
import timber.log.Timber

class ProgressBarHelper {

    // where to insert progress view
    companion object {
        private const val ROOT = 0
        private const val CONTAINER = 1
    }

    private var progressLayout: RelativeLayout? = null

    constructor(context: Context) {
        val layout = (context as Activity).findViewById<ViewGroup>(android.R.id.content)
        addProgressBar(context, layout, ROOT)
    }

    /**
     * Better choose FrameLayout or RelativeLayout as container.
     * Doesn't support ViewPager, LinearLayout as container.
     */
    constructor(layout: ViewGroup, containerId: Int) {
        val context = layout.context
        val container = layout.findViewById<ViewGroup>(containerId)

        if (container != null && container !is LinearLayout) {
            addProgressBar(context, container, CONTAINER)
        } else {
            addProgressBar(context, layout, ROOT)
            if (BuildConfig.DEBUG) {
                context.toast("Wrong container id or LinearLayout based group used",
                        Toast.LENGTH_LONG)
            }
            Timber.e("Wrong container id or LinearLayout based group used used. " +
                    "See ProgressBarHelper.kt")
        }
    }

    private fun addProgressBar(context: Context, layout: ViewGroup, view: Int) {
        val progress: RelativeLayout? = if (view == CONTAINER) {
            layout.findViewById(R.id.rl_progress_view_container)
        } else {
            layout.findViewById(R.id.rl_progress_view_root)
        }

        if (progress == null) {
            // activity hasn't progress view yet
            val progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleLarge)
            progressBar.isIndeterminate = true

            val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT)

            progressLayout = RelativeLayout(context)
            with (progressLayout!!) {
                gravity = Gravity.CENTER
                setBackgroundColor(context.getColorCompat(R.color.grey_f3))
                addView(progressBar)

                id = if (view == CONTAINER) {
                    R.id.rl_progress_view_container
                } else {
                    R.id.rl_progress_view_root
                }
            }

            layout.addView(progressLayout, params)

            hide()
        } else {
            // activity already has progress view
            progressLayout = progress
            // lift up the view
            layout.removeView(progress)
            layout.addView(progress)
        }
    }

    fun show() {
        progressLayout!!.visibility = View.VISIBLE
    }

    fun hide() {
        progressLayout!!.visibility = View.INVISIBLE
    }
}