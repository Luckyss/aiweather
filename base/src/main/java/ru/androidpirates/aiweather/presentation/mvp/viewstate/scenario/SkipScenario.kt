package ru.androidpirates.aiweather.presentation.mvp.viewstate.scenario

import ru.androidpirates.aiweather.presentation.mvp.viewstate.action.ViewAction

class SkipScenario : ActionScenario {
    override fun afterCall(actionsQueue: MutableCollection<Pair<String, ViewAction>>,
                           action: ViewAction) {
    }

    override fun beforeCall(actionsQueue: MutableCollection<Pair<String, ViewAction>>,
                            action: ViewAction) {
    }
}