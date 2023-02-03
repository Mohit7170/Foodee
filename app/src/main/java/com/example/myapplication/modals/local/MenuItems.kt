package com.example.myapplication.modals

import android.content.Context
import com.example.myapplication.classes.AppDatabase
import com.example.myapplication.modals.db.MenuItem

data class MenuItem(
    val itemId: Int,
    val itemName: String,
    val itemPrice: Double,
    val itemImage: String,
    val isInCart: Boolean
)

class DemoMenuItems {

    companion object {
        fun getItems(context: Context): List<MenuItem> {
            val items = AppDatabase.getInstance(context).menuDao().getAll()
            if (items.isEmpty()) addIteToDb(context)
            return items
        }

        private fun addIteToDb(context: Context) {
            AppDatabase.getInstance(context).menuDao().insertAll(
                MenuItem(itemId = 0, itemName = "", itemPrice = 10.0, itemImage = "", isInCart = false)
            )

        }
    }
}
