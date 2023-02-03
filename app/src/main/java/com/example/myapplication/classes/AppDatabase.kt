package com.example.myapplication.classes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.interfaces.db.CartItemsDAO
import com.example.myapplication.interfaces.db.MenuItemDAO
import com.example.myapplication.modals.db.CartItems
import com.example.myapplication.modals.db.MenuItem

@Database(entities = [MenuItem::class,CartItems::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartItemsDAO
    abstract fun menuDao(): MenuItemDAO

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, "MyAppDB")
            .build()
    })

}