package com.example.myapplication.uttils

interface Params {

    companion object{
        val SHARED_PREF_NAME = "MyAppSharedPref"
        val INTENT_KEY_IS_FROM_CART = "isFromCart"
        val DEFAULT_EMPTY_STRING = "-"


        //Shared Pref Keys

        val HAS_ITEM_IN_CART = "hasItemInCart"
        val CART_ITEMS = "cartItens"
        val CART_VALUE = "cartValue"
    }


}