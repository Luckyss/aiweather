package ru.androidpirates.aiweather.presentation.mvp.viewstate.scenario

import ru.androidpirates.aiweather.presentation.mvp.viewstate.action.ViewAction

interface ActionScenario {
    fun beforeCall(actionsQueue: MutableCollection<Pair<String, ViewAction>>, action: ViewAction)
    fun afterCall(actionsQueue: MutableCollection<Pair<String, ViewAction>>, action: ViewAction)
}