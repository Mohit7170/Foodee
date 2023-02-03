package com.example.myapplication.uttils

interface Params {

    companion object {
        val SHARED_PREF_NAME = "MyAppSharedPref"
        val INTENT_KEY_IS_FROM_CART = "isFromCart"
        val DEFAULT_EMPTY_STRING = "-"


        //Shared Pref Keys
        const val HAS_ITEM_IN_CART = "hasItemInCart"
        const val SP_CART_ITEMS = "cartItens"
        const val SP_CART_VALUE = "cartValue"
        const val SP_APP_LANG = "appLand"
        const val SP_KEY_USER_LANGUAGE = "userLanguage"
    }

    enum class Languages(val key: String, val language: String) {
        ENGLISH("en", "English"),
        GERMAN("de", "German"),
        HINDI("hi", "Hindi"),
        SPANISH("es", "Spanish"),
        PUNJABI("pa", "Punjabi")
    }

}