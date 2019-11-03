package com.vladgrushevoy.homework3_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {
    var adapter: DataAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)
        val list = rootView.findViewById(R.id.list_of_ssd) as RecyclerView
        list.layoutManager = LinearLayoutManager(activity)
        list.adapter  = adapter
        return rootView
    }
}