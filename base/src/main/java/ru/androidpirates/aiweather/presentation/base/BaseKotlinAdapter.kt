package ru.androidpirates.aiweather.presentation.base

import android.support.annotation.CallSuper
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.androidpirates.aiweather.common.extensions.customTag
import ru.androidpirates.aiweather.common.extensions.disposeSafely
import timber.log.Timber
import java.util.*

abstract class BaseKotlinAdapter<M, H : BaseKotlinAdapter.Holder<M>> : RecyclerView.Adapter<H>() {
    private var mLastQuery: String? = null
    protected val mItems: MutableList<M> = ArrayList()
    protected val mItemsAll: MutableList<M> = ArrayList()

    open fun getItems(): MutableList<M> {
        return mItems
    }

    open fun setItems(items: List<M>) {
        setItems(items, false)
    }

    protected open fun createDiffCallback(oldItems: List<M>, newItems: List<M>): BaseDiffCallback<M>? {
        return null
    }

    open fun detectMoves(): Boolean = true

    private fun setItems(items: List<M>?, isSearchMode: Boolean) {
        if (!isSearchMode) {
            mItems.clear()
            mItemsAll.clear()
            if (items == null) return

            val callback = createDiffCallback(mItems, items)
            if (callback == null) {
                mItems.addAll(items)
                mItemsAll.addAll(items)
                notifyDataSetChanged()
            } else {
                val result = DiffUtil.calculateDiff(callback, detectMoves())
                mItems.addAll(items)
                mItemsAll.addAll(items)
                result.dispatchUpdatesTo(this)
            }
            find(mLastQuery)
        } else {
            mItems.clear()
            if (items == null) return

            val callback = createDiffCallback(mItems, items)
            if (callback == null) {
                mItems.addAll(items)
                notifyDataSetChanged()
            } else {
                val result = DiffUtil.calculateDiff(callback, detectMoves())
                mItems.addAll(items)
                result.dispatchUpdatesTo(this)
            }
        }
    }

    @CallSuper open fun addItems(items: List<M>?) {
        if (items == null) return

        val oldSize = mItems.size
        mItems.addAll(items)
        mItemsAll.addAll(items)
        notifyItemRangeInserted(oldSize, items.size)
    }

    override fun onViewRecycled(holder: H) {
        holder.detachHolder()
    }

    open fun find(query: String?): Boolean {
        val answer: Boolean
        mLastQuery = query
        val predicate = searchPredicate(query)

        var items = mItemsAll

        if (!TextUtils.isEmpty(mLastQuery)) {
            if (predicate != null) {
                items = mItemsAll.filter(predicate).toMutableList()
                answer = false
            } else {
                Timber.e("Способ поиска не был установлен. "
                    + "Переопределите метод searchPredicate(String)", customTag())
                answer = false
            }
        } else {
            answer = true
        }

        if (mItems != items) {
            setItems(items, true)
        }
        return answer
    }

    open fun searchPredicate(query: String?): ((M) -> Boolean)? = null

    @CallSuper open fun clear() {
        mItemsAll.clear()
        mItems.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: H, position: Int) = holder.attachHolder(mItems[position])
    override fun getItemCount(): Int = mItems.size

    abstract class Holder<in M>(view: View) : RecyclerView.ViewHolder(view) {
        private val disposables: CompositeDisposable = CompositeDisposable()

        abstract fun attachHolder(model: M)

        fun unsubscribeOnDetach(disposable: Disposable) {
            disposables.add(disposable)
        }

        @CallSuper open fun detachHolder() {
            disposables.disposeSafely()
        }
    }
}
