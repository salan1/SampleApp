package com.example.myapplication.domain.interactors.base

import java.util.*

/**
 * Used to pass parameters to {BaseUseCaseObservable} instances.
 */
class Params {

    private val parameters = HashMap<String, Any>()

    fun putInt(key: String, value: Int) {
        parameters[key] = value
    }

    fun getInt(key: String, defaultValue: Int): Int {
        val obj = parameters[key] ?: return defaultValue
        return try {
            obj as Int
        } catch (e: ClassCastException) {
            defaultValue
        }
    }

    fun putFloat(key: String, value: Float) {
        parameters[key] = value
    }

    fun getFloat(key: String, defaultValue: Float?): Float? {
        val obj = parameters[key] ?: return defaultValue
        return try {
            obj as Float
        } catch (e: ClassCastException) {
            defaultValue
        }
    }

    fun putLong(key: String, value: Long) {
        parameters[key] = value
    }

    fun getLong(key: String, defaultValue: Long): Long {
        val obj = parameters[key] ?: return defaultValue
        return try {
            obj as Long
        } catch (e: ClassCastException) {
            defaultValue
        }

    }

    fun putString(key: String, value: String) {
        parameters[key] = value
    }

    fun getString(key: String, defaultValue: String?): String? {
        val obj = parameters[key] ?: return defaultValue
        return try {
            obj as String
        } catch (e: ClassCastException) {
            defaultValue
        }
    }

    fun putBoolean(key: String, value: Boolean) {
        parameters[key] = value
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val obj = parameters[key] ?: return defaultValue
        return try {
            obj as Boolean
        } catch (e: ClassCastException) {
            defaultValue
        }
    }

    fun putObject(key: String, obj: Any) {
        parameters[key] = obj
    }

    fun getObject(key: String): Any {
        return parameters[key]!!
    }

    companion object {
        fun create(): Params {
            return Params()
        }

        val EMPTY = create()
    }
}