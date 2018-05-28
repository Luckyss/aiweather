package ru.androidpirates.aiweather.common.decorations

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

import ru.androidpirates.aiweather.common.util.UiUtils

class SpaceItemDecoration(private val spaceDp: Float) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State?
    ) {
        val space = UiUtils.dpToPx(spaceDp)
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space
        }
        outRect.left = space
        outRect.right = outRect.left
        outRect.bottom = outRect.right
    }
}