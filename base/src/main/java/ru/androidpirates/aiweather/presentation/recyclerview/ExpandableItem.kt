package ru.androidpirates.aiweather.presentation.recyclerview


class ExpandableItem<out T>(expType: Int,
                            expModel: T,
                            var isExpanded: Boolean = false
) : DisplayableItem<T>(expType, expModel)

inline fun <reified T : Any> T.toExpandableItem(type: Int): ExpandableItem<T> {
    return ExpandableItem(type, this)
}