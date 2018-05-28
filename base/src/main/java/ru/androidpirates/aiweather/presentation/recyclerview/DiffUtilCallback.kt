package ru.androidpirates.aiweather.presentation.recyclerview

import android.support.v7.util.DiffUtil


class DiffUtilCallback(
        private val oldItems: List<DisplayableItem<Any>>,
        private val newItems: List<DisplayableItem<Any>>,
        private val comparator: ItemComparator
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return comparator.areItemsTheSame(oldItems[oldItemPosition],
                newItems[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return comparator.areContentsTheSame(oldItems[oldItemPosition],
                newItems[newItemPosition])
    }
}