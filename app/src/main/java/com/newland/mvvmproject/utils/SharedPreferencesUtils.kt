package com.newland.mvvmproject.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * @author: leellun
 * @data: 16/6/2021.
 *
 */
object SharedPreferencesUtils {
    private const val DEFAULT_NAME = "share_preferences"
    private var mSharedPreferences: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null

    private fun SharedPreferencesUtils(context: Context, name: String) {
        mSharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        mEditor = mSharedPreferences.edit()
    }

    operator fun get(name: String): SharedPreferencesUtils? {
        return SharedPreferencesUtils(BaseApplication.getContext(), name)
    }

    fun get(): SharedPreferencesUtils? {
        return SharedPreferencesUtils(BaseApplication.getContext(), DEFAULT_NAME)
    }

    fun getAll(): Map<String?, *>? {
        return mSharedPreferences!!.all
    }

    @Nullable
    fun getString(key: String?, defaultValue: String?): String? {
        return mSharedPreferences!!.getString(key, defaultValue)
    }

    @Nullable
    fun getStringSet(s: String?, set: Set<String?>?): Set<String?>? {
        return mSharedPreferences!!.getStringSet(s, set)
    }

    fun getInt(s: String?, i: Int): Int {
        return mSharedPreferences!!.getInt(s, i)
    }

    fun getLong(s: String?, l: Long): Long {
        return mSharedPreferences!!.getLong(s, l)
    }

    fun getFloat(s: String?, v: Float): Float {
        return mSharedPreferences!!.getFloat(s, v)
    }

    fun getBoolean(s: String?, b: Boolean): Boolean {
        return mSharedPreferences!!.getBoolean(s, b)
    }

    operator fun contains(s: String?): Boolean {
        return mSharedPreferences!!.contains(s)
    }

    fun putString(key: String?, value: String?) {
        mEditor!!.putString(key, value)
        mEditor!!.commit()
    }

    fun putStringSet(key: String?, values: Set<String?>?) {
        mEditor!!.putStringSet(key, values)
        mEditor!!.commit()
    }

    fun putInt(key: String?, value: Int) {
        mEditor!!.putInt(key, value)
        mEditor!!.commit()
    }

    fun putLong(key: String?, value: Long) {
        mEditor!!.putLong(key, value)
        mEditor!!.commit()
    }

    fun putFloat(key: String?, value: Float) {
        mEditor!!.putFloat(key, value)
        mEditor!!.commit()
    }

    fun putBoolean(key: String?, value: Boolean) {
        mEditor!!.putBoolean(key, value)
        mEditor!!.commit()
    }

    fun remove(key: String?) {
        mEditor!!.remove(key)
        mEditor!!.commit()
    }

    fun clear() {
        mEditor!!.clear()
        mEditor!!.commit()
    }
}