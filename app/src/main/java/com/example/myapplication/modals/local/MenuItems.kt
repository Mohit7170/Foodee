package com.example.myapplication.modals

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import com.example.myapplication.R
import com.example.myapplication.classes.AppDatabase
import com.example.myapplication.modals.db.MenuItem


class DemoMenuItems {

    companion object {
        fun getItems(context: Context): List<MenuItem> {
            val items = AppDatabase.getInstance(context).menuDao().getAll()
            if (items.isEmpty()) addIteToDb(context)
            return items
        }

        fun initData(context: Context){
            if (AppDatabase.getInstance(context).menuDao().getAll().isEmpty()) addIteToDb(context)
        }
        fun addIteToDb(context: Context) {
            AppDatabase.getInstance(context).menuDao().insertAll(
                MenuItem(
                    itemId = 0,
                    itemName = "Burger",
                    itemPrice = 100.0,
                    itemImage = R.drawable.img_1,
                    isInCart = false
                ),
                MenuItem(
                    itemId = 1,
                    itemName = "Pizza",
                    itemPrice = 80.0,
                    itemImage = R.drawable.img_2,
                    isInCart = false
                ),
                MenuItem(
                    itemId = 2,
                    itemName = "Rice",
                    itemPrice = 150.0,
                    itemImage = R.drawable.img_3,
                    isInCart = false
                ),
                MenuItem(
                    itemId = 3,
                    itemName = "Food",
                    itemPrice = 180.0,
                    itemImage = R.drawable.img_4,
                    isInCart = false
                ),
            )

        }
    }
}
