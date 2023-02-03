package com.example.myapplication.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.classes.AppDatabase
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.interfaces.Clicked
import com.example.myapplication.modals.DemoMenuItems
import com.example.myapplication.ui.adapters.MenuAdapter

class MainActivity : AppCompatActivity(), Clicked {

    private lateinit var activity: Activity
    private lateinit var binding: ActivityMainBinding

    private var itemCount = 0

    private var isFromCart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isFromCart = intent.getBooleanExtra("isFromCart", false)

        if (isFromCart) makeMenuLyt()
        else makeCartLyt()
    }

    private fun makeCartLyt() {
        with(binding) {
            cartGrp.visibility = View.GONE

            val cartItems = AppDatabase.getInstance(activity).cartDao().getAll()

            if (cartItems.isNotEmpty()) {
                bookingsRv.visibility = View.VISIBLE
                bookingsRv.adapter = MenuAdapter(activity, cartItems, this@MainActivity)
            }
        }
    }

    private fun makeMenuLyt() {
        with(binding) {
            val menuItems = DemoMenuItems.getItems(activity)

            if (menuItems.isNotEmpty()) {
                bookingsRv.visibility = View.VISIBLE
                bookingsRv.adapter = MenuAdapter(activity, menuItems, this@MainActivity)
            }

            val cartItems = AppDatabase.getInstance(activity).cartDao().getAll()

            cartGrp.visibility = if (cartItems.isEmpty()) View.GONE else View.VISIBLE

            viewCartTv.setOnClickListener {
                startActivity(
                    Intent(
                        activity,
                        CartActivity::class.java
                    )
                )
            }
        }
    }

    override fun performAction(itemId: Int) {
        if(!isFromCart) {
            with(binding) {
                if (itemId > 0) {
                    cartGrp.visibility = View.VISIBLE
                    priceTv.text = " 1111"

                } else cartGrp.visibility = View.GONE
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }


}