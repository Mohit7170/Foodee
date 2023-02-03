package com.example.myapplication.interfaces.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.modals.db.MenuItem

@Dao
interface MenuItemDAO {
    @Query("SELECT * FROM MenuItem")
    fun getAll(): List<MenuItem>

    @Insert
    fun insertAll(vararg users: MenuItem)

    @Delete
    fun delete(user: MenuItem)
}


@Dao
interface CartItemsDAO {
    @Query("SELECT * FROM CartItems")
    fun getAll(): List<MenuItem>

    @Insert
    fun addItem(users: MenuItem)

    @Delete
    fun deleteItem(user: MenuItem)
}