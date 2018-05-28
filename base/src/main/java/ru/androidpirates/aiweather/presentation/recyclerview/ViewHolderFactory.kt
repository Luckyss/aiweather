package ru.androidpirates.aiweather.presentation.recyclerview

import android.view.ViewGroup

/**
 * Instantiates a [RecyclerViewAdapter.ViewHolder] based on the type.
 *
 */
interface ViewHolderFactory {

    /**
     * Creates a [RecyclerViewAdapter.ViewHolder]
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @return the newly created [RecyclerViewAdapter.ViewHolder]
     */
    fun createViewHolder(
            parent: ViewGroup,
            itemClickListener: RecyclerViewAdapter.ItemClickListener
    ): RecyclerViewAdapter.ViewHolder
}