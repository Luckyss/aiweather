package ru.androidpirates.aiweather.data

import io.reactivex.functions.Function


interface EssentialMapper<T : Any, R> : Function<T, R> {

    @Throws(EssentialParamMissingException::class)
    override fun apply(raw: T): R {
        getMissedParams(raw)?.let {
            if (it.isNotEmpty()) throw EssentialParamMissingException(it, raw)
        }

        return applyMap(raw)
    }

    fun applyMap(raw: T): R

    fun getMissedParams(raw: T): List<String>?
}