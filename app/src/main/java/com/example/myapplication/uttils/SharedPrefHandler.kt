package com.example.myapplication.uttils

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.uttils.Params.Companion.SHARED_PREF_NAME

class SharedPrefHandler(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    fun setIntValue(key: String?, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getIntFromSharedPref(key: String?): Int {
        return sharedPreferences.getInt(key, -1)
    }

    fun setStringValue(key: String?, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringFromSharedPref(key: String?): String {
        return sharedPreferences.getString(key, "-") ?: "-"
    }

    fun setAppLanguage(key: Params.Languages) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(SP_KEY_USER_LANGUAGE, key.key)
        editor.apply()
    }

    fun getAppLanguageKey(): String {
        return sharedPreferences.getString(SP_KEY_USER_LANGUAGE, Params.Languages.ENGLISH.key)!!
    }


}