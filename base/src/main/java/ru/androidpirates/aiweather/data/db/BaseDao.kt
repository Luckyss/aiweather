package ru.androidpirates.aiweather.data.db

import io.reactivex.Single

/**
 * Base Dao object. This interface must implement all Dao objects.
 */
interface BaseDao<in Key, Entity> {

    fun getAll(): Single<List<Entity>>

    fun getSingular(key: Key): Single<Entity>

    fun insertAll(messages: List<Entity>)

    fun insert(vararg message: Entity)

    fun delete(message: Entity)

    fun clearTable()

    companion object {
        const val SELECT = "SELECT "
        const val FROM = " FROM "
        const val SELECT_ALL_FROM = "$SELECT*$FROM"
        const val WHERE = " WHERE "
        const val DELETE = "DELETE "
        const val DELETE_FROM = "$DELETE$FROM"
    }
}