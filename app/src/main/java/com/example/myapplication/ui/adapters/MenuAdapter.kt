package com.example.myapplication.ui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.classes.AppDatabase
import com.example.myapplication.databinding.ItemContainerMenuBinding
import com.example.myapplication.interfaces.Clicked
import com.example.myapplication.modals.db.MenuItem
import com.example.myapplication.uttils.Params
import com.example.myapplication.uttils.SharedPrefHandler

class MenuAdapter(
    private val activity: Activity,
    private val items: List<MenuItem>,
    private val clicked: Clicked
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContainerMenuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {

            val item = items[position]

            itemNameTv.text = item.itemName
            itemPriceTv.text = "₹ ${item.itemPrice}"
            itemIv.setImageResource(item.itemImage)

            if (!item.isInCart) {
                addBtn.text = "Add"
                addBtn.setOnClickListener {
                    val pos = holder.adapterPosition
                    val addItem = items[pos]

                    addItem.isInCart = true
                    AppDatabase.getInstance(activity).menuDao().updateItem(addItem)

                    val items =
                        SharedPrefHandler(activity).getIntFromSharedPref(Params.CART_ITEMS) + 1
                    val oldCartVal =
                        SharedPrefHandler(activity).getIntFromSharedPref(Params.CART_VALUE) + item.itemPrice

                    SharedPrefHandler(activity).setIntValue(Params.CART_ITEMS, items)
                    SharedPrefHandler(activity).setIntValue(Params.CART_VALUE, oldCartVal.toInt())

                    clicked.performAction(addItem.itemId)

                    notifyItemChanged(pos)

//                SharedPrefHandler(activity).setBooleanValue(Params.HAS_ITEM_IN_CART, true)
                }
            } else addBtn.text = "In Cart"

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: ItemContainerMenuBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val TAG = "MenuAdapter"
    }
}