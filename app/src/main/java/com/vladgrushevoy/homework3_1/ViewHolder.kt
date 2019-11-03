package com.vladgrushevoy.homework3_1

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ssd_item_list.view.*


class CustomViewHolder(view: View, private val onItemListener: OnItemListener) :
    RecyclerView.ViewHolder(view) {
    val image: ImageView = view.ssd_image
    val name: TextView = view.name
    val listListener = view.setOnClickListener {
        onItemListener.onClickItem(adapterPosition)
    }
}