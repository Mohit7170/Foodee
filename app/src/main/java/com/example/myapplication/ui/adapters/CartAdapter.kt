package com.example.myapplication.ui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.classes.AppDatabase
import com.example.myapplication.databinding.ItemContainerCartBinding
import com.example.myapplication.interfaces.Clicked
import com.example.myapplication.modals.db.MenuItem
import com.example.myapplication.uttils.Params
import com.example.myapplication.uttils.SharedPrefHandler

class CartAdapter(
    private val activity: Activity,
    private val items: MutableList<MenuItem>,
    private val clicked: Clicked
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContainerCartBinding.inflate(
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

            crossIv.setOnClickListener {
                removeItem(item, holder.adapterPosition)
//                clicked.performAction(items[holder.adapterPosition].itemId)
            }
        }
    }

    private fun removeItem(item: MenuItem, position: Int) {
        item.isInCart = false
        AppDatabase.getInstance(activity).menuDao().updateItem(item)
        items.removeAt(position)

//        if(items.isEmpty())
        SharedPrefHandler(activity).setIntValue(Params.CART_ITEMS, items.count())

        val oldCartVal =
            SharedPrefHandler(activity).getIntFromSharedPref(Params.CART_VALUE) - item.itemPrice

        SharedPrefHandler(activity).setIntValue(
            Params.CART_VALUE,
            if (oldCartVal <= 0) 0 else oldCartVal.toInt()
        )

//        SharedPrefHandler(activity).setBooleanValue(Params.HAS_ITEM_IN_CART,false)

        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: ItemContainerCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val TAG = "MenuAdapter"
    }
}