package ru.androidpiraters.aiweather.presentation

import ru.androidpirates.aiweather.presentation.recyclerview.DisplayableItem
import ru.androidpirates.aiweather.presentation.recyclerview.ItemComparator

class WeatherItemComparator : ItemComparator {

    override fun areItemsTheSame(
            item1: DisplayableItem<Any>,
            item2: DisplayableItem<Any>
    ): Boolean {
        return item1.model == item2.model
    }

    override fun areContentsTheSame(
            item1: DisplayableItem<Any>,
            item2: DisplayableItem<Any>
    ): Boolean {
        return item1.model == item2.model
    }
}