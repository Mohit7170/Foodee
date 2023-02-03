package com.example.myapplication.interfaces.db

import androidx.room.*
import com.example.myapplication.modals.db.MenuItem

@Dao
interface MenuItemDAO {
    @Query("SELECT * FROM MenuItem")
    fun getAll(): List<MenuItem>

    @Query("SELECT * FROM MenuItem WHERE isInCart = true")
    fun getCartItems(): List<MenuItem>

    @Insert
    fun insertAll(vararg item: MenuItem)

    @Update
    fun updateItem(item: MenuItem)

    @Delete
    fun delete(user: MenuItem)
}


//@Dao
//interface CartItemsDAO {
//    @Query("SELECT * FROM CartItems")
//    fun getAll(): List<MenuItem>
//
//    @Insert
//    fun addItem(users: MenuItem)
//
//    @Delete
//    fun deleteItem(user: MenuItem)
//}