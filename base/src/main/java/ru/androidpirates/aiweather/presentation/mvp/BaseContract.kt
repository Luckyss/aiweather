package ru.androidpirates.aiweather.presentation.mvp

import android.arch.lifecycle.Lifecycle
import ru.androidpirates.aiweather.common.constants.Constants
import ru.androidpirates.aiweather.presentation.mvp.viewstate.action.ActionConfig
import ru.androidpirates.aiweather.presentation.mvp.viewstate.scenario.SkipScenario

interface BaseContract {

    interface View : MvpView {

        @ActionConfig(scenario = SkipScenario::class) fun logout()

        @ActionConfig(scenario = SkipScenario::class) fun showError(errorThrowable: Throwable)

        @ActionConfig(scenario = SkipScenario::class)
        fun showMessage(messageString: String = "", messageId: Int = Constants.NO_ID_INT)

        @ActionConfig("progress") fun showProgress()

        @ActionConfig("progress") fun hideProgress()
    }

    interface Presenter<V : View> {

        fun getView(): V

        fun attachToLifecycle(lifecycle: Lifecycle)

        fun detachFromLifecycle(lifecycle: Lifecycle)

        fun firstAttachView(view: V)

        fun attachView(view: V)

        fun detachView()

        fun onPresenterDestroy()
    }
}