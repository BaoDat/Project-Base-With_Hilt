package com.datdang.projectbase.data.session

import android.content.Context
import android.content.SharedPreferences
import com.datdang.projectbase.data.BuildConfig
import timber.log.Timber

open class AppSharedPreference(context: Context, private val keyPrefix: String) {

    private val sharedPreference: SharedPreferences = context.getSharedPreferences(BuildConfig.APP_NAME, Context.MODE_PRIVATE)

    fun set(key: String, value: String) {
        try {
            sharedPreference.apply { it.putString(genKey(key), value) }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    fun getValue(key: String): String? {
        return getValue(key, "")
    }

    fun getValue(key: String, defaultValue: String?): String? {
        try {
            return sharedPreference.getString(genKey(key), defaultValue)
        } catch (e: Exception) {
            Timber.e(e)
        }
        return defaultValue
    }

    fun remove(key: String) {
        try {
            sharedPreference.apply { it.remove(genKey(key)) }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    fun removeAll() {
        try {
            sharedPreference.apply { it.clear() }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    fun removeAllOwnPrefix() {
        try {
            sharedPreference.apply {
                val allEntries: Map<String, *> = sharedPreference.all
                for ((key) in allEntries) {
                    if (key.startsWith(keyPrefix)) {
                        it.remove(key)
                    }
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private inline fun SharedPreferences.apply(action: (SharedPreferences.Editor) -> Unit) {
        edit().run {
            action(this)
            apply()
        }
    }

    private fun genKey(key: String): String {
        return "$keyPrefix.$key"
    }
}