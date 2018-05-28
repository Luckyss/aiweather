package ru.androidpirates.aiweather.presentation.base

import android.os.Bundle
import android.support.v7.util.DiffUtil

open class BaseDiffCallback<T>(protected var mOldList: List<T>?, protected var mNewList: List<T>?) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList!![oldItemPosition] == mNewList!![newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList!![oldItemPosition] == mNewList!![newItemPosition]
    }

    override fun getOldListSize(): Int {
        return if (mOldList != null) mOldList!!.size else 0
    }

    override fun getNewListSize(): Int {
        return if (mNewList != null) mNewList!!.size else 0
    }

    protected fun payload(oldItemPosition: Int, newItemPosition: Int): Bundle? {
        return null
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val diffBundle = payload(oldItemPosition, newItemPosition)
        return if (diffBundle == null || diffBundle.size() == 0) {
            null
        } else {
            diffBundle
        }
    }
}
