package com.example.myapplication.ui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemContainerMenuBinding
import com.example.myapplication.interfaces.Clicked
import com.example.myapplication.modals.db.MenuItem

class MenuAdapter(
    private val activity: Activity,
    private val items: List<MenuItem>,
    private val clicked:Clicked
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
        with(holder.binding){

            val item = items[position]

            itemNameTv.text = item.itemName
            itemPriceTv.text = "₹ ${item.itemPrice}"

            addBtn.setOnClickListener {
                clicked.performAction(items[holder.adapterPosition].itemId)
            }
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