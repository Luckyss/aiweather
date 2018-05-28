package ru.androidpirates.aiweather.presentation.recyclerview


interface ItemComparator {

    /**
     * Decides whether two [DisplayableItem] represent the same Item.
     * For example, if your items have unique ids, this method should check their id equality.
     * @return True if the two items represent the same object or false if they are different.
     */
    fun areItemsTheSame(item1: DisplayableItem<Any>, item2: DisplayableItem<Any>): Boolean

    /**
     * Checks whether the visual representation of two [DisplayableItem]s is the same.
     *
     * This method is called only if [areItemsTheSame]
     * returns `true` for these items. For instance, when the item is the same with different
     * state, like selected.
     * @return True if the visual representation for the [DisplayableItem]s are the same or
     * false if they are different.
     */
    fun areContentsTheSame(item1: DisplayableItem<Any>, item2: DisplayableItem<Any>): Boolean
}