package ru.androidpirates.aiweather.presentation.recyclerview


open class DisplayableItem<out T>(val type: Int, val model: T)


inline fun <reified T : Any> T.toDisplayableItem(type: Int): DisplayableItem<T> {
    return DisplayableItem(type, this)
}