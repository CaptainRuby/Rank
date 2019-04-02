package com.ruby.rank.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("ApplySharedPref")
class Config(context: Context) {

    companion object {

        val SUBJECT_COUNT = "SUBJECT_COUNT"
    }

    private val pref: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun get(tag: String, value: String): String? {
        return pref.getString(tag, value)
    }

    fun get(tag: String, value: Int): Int {
        return pref.getInt(tag, value)
    }

    fun get(tag: String, value: Float): Float {
        return pref.getFloat(tag, value)
    }

    fun get(tag: String, value: Long): Long {
        return pref.getLong(tag, value)
    }

    fun get(tag: String, value: Boolean): Boolean? {
        return pref.getBoolean(tag, value)
    }

    fun set(tag: String, value: String) {
        pref.edit().putString(tag, value).commit()
    }

    fun set(tag: String, value: Int) {
        pref.edit().putInt(tag, value).commit()
    }

    fun set(tag: String, value: Long) {
        pref.edit().putLong(tag, value).commit()
    }

    fun set(tag: String, value: Float) {
        pref.edit().putFloat(tag, value).commit()
    }

    fun set(tag: String, value: Boolean) {
        pref.edit().putBoolean(tag, value).commit()
    }

    fun delete(tag: String) {
        pref.edit().remove(tag).commit()
    }

    fun clearAll() {
        pref.edit().clear().commit()
    }


}
