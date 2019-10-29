package com.vladgrushevoy.homework3_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class Adapter(private  val values: List<String>): RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView?.text = values[position]
    }

    override fun getItemCount(): Int {
        return  values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.animal_item_list, parent, false)
        return ViewHolder(itemView)
    }


    class ViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!){
        var textView : TextView? = null
        init{
            textView = itemView?.findViewById(R.id.text_id_item)
        }
    }
}