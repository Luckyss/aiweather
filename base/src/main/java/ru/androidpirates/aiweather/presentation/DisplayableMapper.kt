package ru.androidpirates.aiweather.presentation

import io.reactivex.functions.Function
import ru.androidpirates.aiweather.presentation.recyclerview.DisplayableItem

interface DisplayableMapper<T> : Function<T, DisplayableItem<T>>
 