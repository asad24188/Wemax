package com.wemax.mtech.Adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.wemax.mtech.R
import android.widget.TextView


class CustomSpinnerSearchAdapter(
    internal var context: Context,
    internal var imagesList: IntArray,
    internal var privacyTextStringList: Array<String>
) :
    BaseAdapter() {
    internal var inflter: LayoutInflater

    init {
        inflter = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return imagesList.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {

        val view = inflter.inflate(R.layout.spinner_item_search_layout, null)
        val icon = view.findViewById<View>(R.id.iconImage) as ImageView?
        val privacyTitle = view.findViewById<View>(R.id.privacy_title) as TextView?
        icon!!.setImageResource(imagesList[i])
        privacyTitle!!.text = privacyTextStringList[i]
        return view
    }

}