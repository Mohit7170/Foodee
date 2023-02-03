package com.example.myapplication.modals

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.classes.AppDatabase
import com.example.myapplication.modals.db.MenuItem
import kotlin.random.Random


class DemoMenuItems {

    companion object {
        fun getItems(context: Context): List<MenuItem> {
            val items = AppDatabase.getInstance(context).menuDao().getAll()
            if (items.isEmpty()) addItemsToDb(context)
            return items
        }

        fun initData(context: Context) {
            if (AppDatabase.getInstance(context).menuDao().getAll().isEmpty()) addItemsToDb(context)
        }

        private fun addItemsToDb(context: Context) {
//            AppDatabase.getInstance(context).menuDao().insertAll(
//                MenuItem(
//                    itemId = 0,
//                    itemName = "Burger",
//                    itemPrice = 100.0,
//                    itemImage = R.drawable.img_1,
//                    isInCart = false
//                ),
//                MenuItem(
//                    itemId = 1,
//                    itemName = "Pizza",
//                    itemPrice = 80.0,
//                    itemImage = R.drawable.img_2,
//                    isInCart = false
//                ),
//                MenuItem(
//                    itemId = 2,
//                    itemName = "Rice",
//                    itemPrice = 150.0,
//                    itemImage = R.drawable.img_3,
//                    isInCart = false
//                ),
//                MenuItem(
//                    itemId = 3,
//                    itemName = "Food",
//                    itemPrice = 180.0,
//                    itemImage = R.drawable.img_4,
//                    isInCart = false
//                ),
//            )
            addRandItemToDb(context)
            addRandItemToDb(context)
            addRandItemToDb(context)
            addRandItemToDb(context)
            addRandItemToDb(context)
        }

        fun addRandItemToDb(context: Context) : MenuItem {

            val images =
                arrayListOf(R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4)
            val itemNames = arrayListOf("Burger", "Pizza", "Rice", "Fries")

            val item = MenuItem(
//                    itemId = 0,
                itemName = itemNames[Random.nextInt(4)],
                itemPrice = Random.nextInt(1000).toDouble(),
                itemImage = images[Random.nextInt(4)],
                isInCart = false
            )

            AppDatabase.getInstance(context).menuDao().insertAll(item)
            return item
        }
    }
}
