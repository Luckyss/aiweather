package ru.androidpirates.aiweather.common.util

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxUtil {
    fun onlyTrue(b: Boolean): Boolean = b

    fun onlyFalse(b: Boolean): Boolean = !b

    //region io-main
    fun ioMain(com: Completable): Completable {
        return com.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> ioMain(single: Single<T>): Single<T> {
        return single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> ioMain(maybe: Maybe<T>): Maybe<T> {
        return maybe.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> ioMain(flowable: Flowable<T>): Flowable<T> {
        return flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> ioMain(obs: Observable<T>): Observable<T> {
        return obs.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
    // endregion

    // region computation-main
    fun computationMain(obs: Completable): Completable {
        return obs.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> computationMain(single: Single<T>): Single<T> {
        return single.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> computationMain(single: Maybe<T>): Maybe<T> {
        return single.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> computationMain(single: Flowable<T>): Flowable<T> {
        return single.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> computationMain(obs: Observable<T>): Observable<T> {
        return obs.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }
    // endregion

    fun doNothing() {
        // still do nothing
    }

    fun <T> doNothing(t: T) {
        // still do nothing
    }

    fun <T, V> observableOfType(t: T, cls: Class<V>): Observable<V> {
        return Observable.just(t)
                .ofType(cls)
    }
}
