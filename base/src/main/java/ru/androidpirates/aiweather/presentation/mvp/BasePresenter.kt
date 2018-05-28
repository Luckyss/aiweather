package ru.androidpirates.aiweather.presentation.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.support.annotation.CallSuper
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.androidpirates.aiweather.base.R
import ru.androidpirates.aiweather.common.extensions.customTag
import ru.androidpirates.aiweather.presentation.mvp.viewstate.ViewState
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BasePresenter<V : BaseContract.View> : LifecycleObserver, BaseContract.Presenter<V> {
    private val compositeDisposable = CompositeDisposable()

    private val viewState = ViewState.create<V>(javaClass)

    final override fun getView(): V {
        return viewState.getView()
    }

    final override fun attachToLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    final override fun detachFromLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    final override fun firstAttachView(view: V) {
        viewState.attachView(view)
        onViewFirstAttached(view)
    }

    final override fun attachView(view: V) {
        viewState.attachView(view)
        onViewAttached(view)
    }

    final override fun detachView() {
        viewState.detachView()
        onViewDetached()
    }

    @CallSuper override fun onPresenterDestroy() {
        compositeDisposable.clear()
    }

    protected open fun onViewFirstAttached(view: V) {
    }

    protected open fun onViewAttached(view: V) {
    }

    protected open fun onViewDetached() {
    }

    protected fun showError(e: Throwable) {
        when (e) {
            is IllegalStateException -> {
                getView().showMessage(messageId = R.string.error_empty_response)
            }
        /*is HttpException -> {
            if (e.code() == HttpURLConnection.HTTP_UNAUTHORIZED
                    || e.code() == HttpURLConnection.HTTP_FORBIDDEN) {

                getView().logout()
            } else {
                getView().showError(e)
            }
        }*/
            is SocketTimeoutException, is UnknownHostException -> {
                getView().showMessage(messageId = R.string.dialog_message_internet)
            }
            else -> getView().showError(e)
        }
        Timber.tag(customTag()).e("Exception: $e")
    }

    protected fun <T> Observable<T>.processLoading(): Observable<T> {
        return doOnSubscribe { getView().showProgress() }
                .doAfterTerminate { getView().hideProgress() }
    }

    protected fun <T> Single<T>.processLoading(): Single<T> {
        return doOnSubscribe { getView().showProgress() }
                .doAfterTerminate { getView().hideProgress() }
    }

    protected fun <T> Flowable<T>.processLoading(): Flowable<T> {
        return doOnSubscribe { getView().showProgress() }
                .doAfterTerminate { getView().hideProgress() }
    }

    protected fun <T> Maybe<T>.processLoading(): Maybe<T> {
        return doOnSubscribe { getView().showProgress() }
                .doAfterTerminate { getView().hideProgress() }
    }

    protected fun disposeOnDestroy(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}
