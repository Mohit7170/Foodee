package com.example.myapplication.ui.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.classes.AppDatabase
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.interfaces.Clicked
import com.example.myapplication.modals.DemoMenuItems
import com.example.myapplication.ui.adapters.CartAdapter
import com.example.myapplication.ui.adapters.MenuAdapter
import com.example.myapplication.uttils.HelperClass
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


        binding.toolbar.setTitle(R.string.app_name)
        binding.toolbar.inflateMenu(R.menu.bottom_nav_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.select_lang -> selectLang()
                R.id.add_new_item -> addItemToDB()
            }
            true
        }

        binding.backIv.setOnClickListener { onBackPressed() }
    }

    private fun addItemToDB() {
        val item = DemoMenuItems.addRandItemToDb(context = activity)
        val adapter = (binding.bookingsRv.adapter as MenuAdapter)
        adapter.addItem(item)
        binding.bookingsRv.scrollToPosition(adapter.itemCount-1)
    }

    private fun makeCartLyt() {
        HelperClass.changeStatusBarColor(activity, R.color.white, true)

        with(binding) {

            topTv.text = getString(R.string.my_cart_text)

            backIv.visibility = View.VISIBLE
            cartGrp.visibility = View.GONE
            toolbar.visibility = View.GONE

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

    private fun selectLang() {

        val checkedItem = intArrayOf(-1)

        val alertDialog = AlertDialog.Builder(this)

        // set the custom icon to the alert dialog
        alertDialog.setIcon(R.drawable.baseline_language_24)

        // title of the alert dialog
        alertDialog.setTitle("Select Language")

        val listItem = arrayListOf<String>()
        enumValues<Params.Languages>().forEach { listItem.add(it.language) }

        // the function setSingleChoiceItems is the function which
        // builds the alert dialog with the single item selection
        alertDialog.setSingleChoiceItems(listItem.toTypedArray(), checkedItem[0]) { dialog, which ->
            // update the selected item which is selected by the user so that it should be selected
            // when user opens the dialog next time and pass the instance to setSingleChoiceItems method
            checkedItem[0] = which

            // now also update the TextView which previews the selected item
            // when selected an item the dialog should be closed with the dismiss method
            val selectedLanguage = listItem[which]

            Log.d(TAG, "selectLang: lang -- $selectedLanguage")

            SharedPrefHandler(activity).setAppLanguage(
                Params.Languages.values().find { it.language == selectedLanguage }!!
            )

            dialog.dismiss()

            startActivity(Intent(activity, SplashActivity::class.java))
            finishAffinity()
        }

        alertDialog.show()
    }

    private fun makeMenuLyt() {
        HelperClass.changeStatusBarColor(activity, R.color.black, false)

        with(binding) {

            topTv.text = getString(R.string.what_s_new_text)
            backIv.visibility = View.GONE
            toolbar.visibility = View.VISIBLE

            val menuItems = DemoMenuItems.getItems(activity)
            Log.d(TAG, "makeMenuLyt: Items -- $menuItems")

            if (menuItems.isNotEmpty()) {
                noDataTv.visibility = View.GONE
                bookingsRv.visibility = View.VISIBLE
                bookingsRv.adapter = MenuAdapter(activity, menuItems.toMutableList(), this@MainActivity)
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
            val cartItems = SharedPrefHandler(activity).getIntFromSharedPref(Params.SP_CART_ITEMS)

            cartGrp.visibility =
                if (cartItems < 0) View.GONE else {
                    itemCount = cartItems

                    priceTv.text =
                        "₹${SharedPrefHandler(activity).getIntFromSharedPref(Params.SP_CART_VALUE)}"

                    View.VISIBLE
                }
        }
    }

    override fun performAction() {
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