package ru.androidpirates.aiweather.presentation.mvp.viewstate.action

interface ViewAction {
    val tag: String
    fun call() : Any?
}