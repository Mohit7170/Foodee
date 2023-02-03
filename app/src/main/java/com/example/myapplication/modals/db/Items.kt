package com.example.myapplication.modals.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "itemId") val itemId: Int,
    @ColumnInfo(name = "name") val itemName: String,
    @ColumnInfo(name = "price") val itemPrice: Double,
    @ColumnInfo(name = "image") val itemImage: Int,
    @ColumnInfo(name = "isInCart") var isInCart: Boolean
)

//@Entity
//data class CartItems(
//    @PrimaryKey(autoGenerate = true) val id: Int=0,
//    @ColumnInfo(name = "itemId") val itemId: Int,
//    @ColumnInfo(name = "name") val itemName: String,
//    @ColumnInfo(name = "price") val itemPrice: Double,
//    @ColumnInfo(name = "image") val itemImage: String,
//    @ColumnInfo(name = "isInCart") val isInCart: Boolean
//)