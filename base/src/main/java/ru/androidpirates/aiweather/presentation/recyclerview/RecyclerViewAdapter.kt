package ru.androidpirates.aiweather.presentation.recyclerview


import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.View
import android.view.ViewGroup
import io.reactivex.Single
import io.reactivex.functions.Consumer
import ru.androidpirates.aiweather.common.preconditions.AndroidPreconditions
import java.util.*
import javax.inject.Inject

/**
 * Implementation of [Adapter] for [DisplayableItem].
 */
open class RecyclerViewAdapter @Inject constructor(
        private val comparator: ItemComparator,
        private val factoryMap: Map<Int, ViewHolderFactory>,
        private val binderMap: Map<Int, ViewHolderBinder>,
        private val androidPreconditions: AndroidPreconditions
) : Adapter<RecyclerViewAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onClick(view: View, rootView: View, position: Int, model: Any = Unit)
    }

    abstract class ViewHolder(
            itemView: View,
            private val itemClickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        protected fun onViewClicked(view: View) {
            itemClickListener.onClick(view, this.itemView, adapterPosition)
        }
    }

    private val internalOnClickListener: ItemClickListener = object : ItemClickListener {
        override fun onClick(view: View, rootView: View, position: Int, model: Any) {
            itemClickListener?.onClick(view, rootView, position, modelItems[position])
        }
    }

    private var itemClickListener: ItemClickListener? = null

    fun setItemClickListener(listener: ItemClickListener?) {
        itemClickListener = listener
    }

    private val modelItems = ArrayList<DisplayableItem<Any>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        return factoryMap[viewType]?.createViewHolder(parent, internalOnClickListener)!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = modelItems[position]
        binderMap[item.type]?.bind(holder, item)
    }

    override fun getItemCount(): Int {
        return modelItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return modelItems[position].type
    }

    /**
     * Updates modelItems currently stored in adapter with the new modelItems.
     *
     * @param items collection to update the previous values
     */
    fun update(items: List<DisplayableItem<Any>>) {
        androidPreconditions.assertUiThread()

        if (modelItems.isEmpty()) {
            updateAllItems(items)
        } else {
            updateDiffItemsOnly(items)
        }
    }

    /**
     * Only use for the first update of the adapter, whe it is still empty.
     */
    private fun updateAllItems(items: List<DisplayableItem<Any>>) {
        Single.just<List<DisplayableItem<Any>>>(items)
                .doOnSuccess({ this.updateItemsInModel(it) })
                .subscribe { _ -> notifyDataSetChanged() }
    }

    /**
     * Do not use for first update of the adapter.
     * The method [DiffUtil.DiffResult.dispatchUpdatesTo] is significantly slower than
     * [RecyclerViewAdapter.notifyDataSetChanged] when it comes to update all the items in the
     * adapter.
     */
    private fun updateDiffItemsOnly(items: List<DisplayableItem<Any>>) {
        // IMPROVEMENT: The diff calculation should happen in the background
        Single.fromCallable<DiffUtil.DiffResult> { calculateDiff(items) }
                .doOnSuccess { _ -> updateItemsInModel(items) }
                .subscribe(Consumer<DiffUtil.DiffResult> { this.updateAdapterWithDiffResult(it) })
    }

    private fun calculateDiff(newItems: List<DisplayableItem<Any>>): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(DiffUtilCallback(modelItems, newItems, comparator))
    }

    private fun updateItemsInModel(items: List<DisplayableItem<Any>>) {
        modelItems.clear()
        modelItems.addAll(items)
    }

    private fun updateAdapterWithDiffResult(result: DiffUtil.DiffResult) {
        result.dispatchUpdatesTo(this)
    }
}