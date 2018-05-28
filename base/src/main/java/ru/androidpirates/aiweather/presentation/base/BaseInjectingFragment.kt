package ru.androidpirates.aiweather.presentation.base

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.androidpirates.aiweather.base.BuildConfig
import ru.androidpirates.aiweather.common.constants.Constants
import ru.androidpirates.aiweather.presentation.mvp.BaseContract
import ru.androidpirates.aiweather.presentation.mvp.WrongViewTypeException
import javax.inject.Inject

abstract class BaseInjectingFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> :
        Fragment(), BaseContract.View {

    @Inject lateinit var presenter: P

    private var progressBarHelper: ProgressBarHelper? = null

    override fun onAttach(context: Context?) {
        onInject()
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val viewGroup = inflater.inflate(getLayoutId(), container, false) as ViewGroup
        val containerId = getProgressContainerId()
        if (containerId == null) {
            bindProgressView()
        } else {
            bindProgressView(viewGroup, containerId)
        }
        return viewGroup
    }

    @CallSuper
    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            presenter.attachView(this as V)
        } catch (e: ClassCastException) {
            throw WrongViewTypeException(
                    "Your ${this.javaClass.simpleName} must have BaseContract.View in hierarchy", e)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    /**
     * Simple progress bar view init. It will covers content and toolbar too.
     * To prevent this need to override this method with
     * *`super.bindProgressView(int)`* call.
     */
    protected fun bindProgressView() {
        progressBarHelper = progressBarHelper ?: ProgressBarHelper(activity!!)
    }

    /**
     * Overloaded method with clarified resource from **activity's** xml.
     * @param containerId resource id.
     */
    protected fun bindProgressView(layout: ViewGroup, containerId: Int) {
        progressBarHelper = progressBarHelper ?: ProgressBarHelper(layout, containerId)
    }

    override fun showProgress() {
        progressBarHelper?.show()
    }

    override fun hideProgress() {
        progressBarHelper?.hide()
    }

    override fun showError(errorThrowable: Throwable) {
        if (BuildConfig.DEBUG) {
            createDialog(errorThrowable.message!!)
        }
    }

    override fun showMessage(messageString: String, messageId: Int) {
        createDialog(if (messageId == Constants.NO_ID_INT) messageString else getString(messageId))
    }

    private fun createDialog(message: String) {
        AlertDialog.Builder(context!!)
                .setTitle("Sorry")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
    }

    protected val application: Application?
        get() = activity?.application

    override fun logout() {
        activity?.finish()
    }

    protected fun setTitle(title: CharSequence) {
        activity?.title = title
    }

    protected fun setTitle(@StringRes id: Int) = activity?.setTitle(id)

    protected fun start(action: String) {
        startActivity(Intent(action))
    }

    abstract fun onInject()

    protected abstract fun getLayoutId(): Int

    protected open fun getProgressContainerId(): Int? {
        return null
    }
}