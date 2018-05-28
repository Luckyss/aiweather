package ru.androidpirates.aiweather.presentation.mvp.viewstate.scenario

import ru.androidpirates.aiweather.presentation.mvp.viewstate.action.ViewAction

class AddToEndSingleScenario : ActionScenario {
    override fun beforeCall(actionsQueue: MutableCollection<Pair<String, ViewAction>>,
                            action: ViewAction) {
        with (actionsQueue) {
            removeAll { it.first == action.tag }
            add(action.tag to action)
        }
    }

    override fun afterCall(actionsQueue: MutableCollection<Pair<String, ViewAction>>,
                           action: ViewAction) {
    }
}