package com.vladgrushevoy.homework3_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

class DataAdapter(
    private val ssd: MutableList<SSD>,
    private val onItemListener: OnItemListener
) : Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.ssd_item_list,
                parent,
                false
            ), onItemListener
        )

    override fun getItemCount() = ssd.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        ssd[position].apply {
            holder.image.setImageResource(image)
            holder.name.text = name
            holder.listListener
        }
    }
}