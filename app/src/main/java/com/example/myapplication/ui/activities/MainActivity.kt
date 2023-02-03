package com.example.myapplication.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.splashscreen.SplashScreen;
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapplication.R
import com.example.myapplication.classes.AppDatabase
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.interfaces.Clicked
import com.example.myapplication.modals.DemoMenuItems
import com.example.myapplication.ui.adapters.CartAdapter
import com.example.myapplication.ui.adapters.MenuAdapter
import com.example.myapplication.uttils.Params
import com.example.myapplication.uttils.SharedPrefHandler


class MainActivity : AppCompatActivity(), Clicked {

    private lateinit var activity: Activity
    private lateinit var binding: ActivityMainBinding

    private var itemCount = 0

    private var isFromCart = false
    private var isLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {

//        val splashScreen = installSplashScreen()
//        splashScreen.setKeepVisibleCondition { isLoading }
        //        DemoMenuItems.initData(this)
//        Handler(Looper.getMainLooper()).postDelayed(2000) { isLoading = false }

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activity = this@MainActivity

        isFromCart = intent.getBooleanExtra(Params.INTENT_KEY_IS_FROM_CART, false)

        binding.backIv.setOnClickListener { onBackPressed() }
    }

    private fun makeCartLyt() {
        with(binding) {

            topTv.text = getString(R.string.my_cart_text)

            backIv.visibility = View.VISIBLE
            cartGrp.visibility = View.GONE

            val cartItems = AppDatabase.getInstance(activity).menuDao().getCartItems()

            if (cartItems.isNotEmpty()) {
                noDataTv.visibility = View.GONE
                bookingsRv.visibility = View.VISIBLE
                bookingsRv.adapter =
                    CartAdapter(activity, cartItems.toMutableList(), this@MainActivity)
            } else {
                noDataTv.visibility = View.VISIBLE
                bookingsRv.visibility = View.GONE
            }
        }
    }

    private fun makeMenuLyt() {
        with(binding) {

            topTv.text = getString(R.string.what_s_new_text)
            backIv.visibility = View.GONE

            val menuItems = DemoMenuItems.getItems(activity)
            Log.d(TAG, "makeMenuLyt: Items -- $menuItems")

            if (menuItems.isNotEmpty()) {
                noDataTv.visibility = View.GONE
                bookingsRv.visibility = View.VISIBLE
                bookingsRv.adapter = MenuAdapter(activity, menuItems, this@MainActivity)
            } else {
                noDataTv.visibility = View.VISIBLE
                bookingsRv.visibility = View.GONE
            }

            checkCart()
            viewCartTv.setOnClickListener {
                startActivity(
                    Intent(
                        activity,
                        MainActivity::class.java
                    ).putExtra(Params.INTENT_KEY_IS_FROM_CART, true)
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkCart()

        if (!isFromCart) makeMenuLyt()
        else makeCartLyt()
    }
//    override fun onRestart() {
//        super.onRestart()
//        checkCart()
//
//    }

    private fun checkCart() {
        with(binding) {
            val cartItems = SharedPrefHandler(activity).getIntFromSharedPref(Params.CART_ITEMS)

            cartGrp.visibility =
                if (cartItems <= 0) View.GONE else {
                    itemCount = cartItems

                    priceTv.text =
                        "₹${SharedPrefHandler(activity).getIntFromSharedPref(Params.CART_VALUE)}"

                    View.VISIBLE
                }
        }
    }

    override fun performAction(itemId: Int) {
        if (!isFromCart) {
            checkCart()
//            with(binding) {
////                if (itemId > 0) {
//                cartGrp.visibility = View.VISIBLE
//                priceTv.text = "₹${SharedPrefHandler(activity).getIntFromSharedPref(Params.CART_VALUE)}"
//
////                } else cartGrp.visibility = View.GONE
//            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }


}