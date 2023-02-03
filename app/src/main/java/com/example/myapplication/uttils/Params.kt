package com.example.myapplication.uttils

interface Params {

    companion object {
        val SHARED_PREF_NAME = "MyAppSharedPref"
        val INTENT_KEY_IS_FROM_CART = "isFromCart"
        val DEFAULT_EMPTY_STRING = "-"


        //Shared Pref Keys

        const val HAS_ITEM_IN_CART = "hasItemInCart"
        const val CART_ITEMS = "cartItens"
        const val CART_VALUE = "cartValue"
        const val APP_LANG = "appLand"
        const val SP_KEY_USER_LANGUAGE = "userLanguage"

    }

    enum class Languages(val key: String, val language: String) {
        ENGLISH("en", "English"),
        GERMAN("de", "German"),
        FRENCH("fr", "French"),

        //        ARABIC("ar", "Arabic"),
        HINDI("hi", "Hindi"),
        CZECH("cs", "Czech"),
        SPANISH("es", "Spanish"),
        PUNJABI("pa", "Punjabi")
    }

}