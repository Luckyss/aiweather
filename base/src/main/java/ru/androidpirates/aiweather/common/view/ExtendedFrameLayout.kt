package ru.androidpirates.aiweather.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import ru.androidpirates.aiweather.common.extensions.getSelectableBackgroundBorderless
import ru.androidpirates.aiweather.common.extensions.setChildrenEnabled

class ExtendedFrameLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun setOnClickListener(l: View.OnClickListener?) {
        super.setOnClickListener(l)
        isClickable = l != null
    }

    override fun setClickable(clickable: Boolean) {
        super.setClickable(clickable)
        foreground = if (clickable && hasOnClickListeners()) {
            context.getSelectableBackgroundBorderless()
        } else {
            null
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        isClickable = enabled
        setChildrenEnabled(enabled)
    }
}
