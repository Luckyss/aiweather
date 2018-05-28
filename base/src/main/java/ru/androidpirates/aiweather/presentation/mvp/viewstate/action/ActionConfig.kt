package ru.androidpirates.aiweather.presentation.mvp.viewstate.action

import ru.androidpirates.aiweather.presentation.mvp.viewstate.scenario.ActionScenario
import ru.androidpirates.aiweather.presentation.mvp.viewstate.scenario.AddToEndSingleScenario
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ActionConfig(
        val tag: String = "",
        val scenario: KClass<out ActionScenario> = AddToEndSingleScenario::class
)