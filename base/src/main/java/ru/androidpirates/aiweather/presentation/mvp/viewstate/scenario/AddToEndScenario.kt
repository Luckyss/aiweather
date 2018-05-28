package ru.androidpirates.aiweather.presentation.mvp.viewstate.scenario

import ru.androidpirates.aiweather.presentation.mvp.viewstate.action.ViewAction

class AddToEndScenario : ActionScenario {
    override fun beforeCall(actionsQueue: MutableCollection<Pair<String, ViewAction>>,
                            action: ViewAction) {
        actionsQueue.add(action.tag to action)
    }

    override fun afterCall(actionsQueue: MutableCollection<Pair<String, ViewAction>>,
                           action: ViewAction) {
    }
}