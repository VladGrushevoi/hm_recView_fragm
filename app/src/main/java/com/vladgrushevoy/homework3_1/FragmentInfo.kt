package com.vladgrushevoy.homework3_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vladgrushevoy.homework3_1.MainActivity.Companion.SSD_KEY
import kotlinx.android.synthetic.main.fragment_info.view.*

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_info, container, false)
        if (arguments != null) {
            rootView.apply {
                val ssd = arguments!!.getSerializable(SSD_KEY) as SSD
                ssd_title?.text = ssd.name
                ssd_image?.setImageResource(ssd.image)
                ssd_description?.text = ssd.description
            }
        }


        return rootView
    }

    companion object {
        fun newInstance(ssd: SSD) = InfoFragment().apply {
            arguments = Bundle().apply {
                putSerializable(SSD_KEY, ssd)
            }
        }
    }
}