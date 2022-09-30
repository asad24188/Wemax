package com.wemax.mtech.Adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.wemax.mtech.R

class SliderAdapter : PagerAdapter {
    var context: Context
    var Images: Array<Int>
    var Title: Array<String>
    var Description: Array<String>
    lateinit var inflater:LayoutInflater

    constructor(context: Context, Images: Array<Int>,Title:Array<String>,Description:Array<String>) : super() {
        this.context = context
        this.Images = Images
        this.Title = Title
        this.Description = Description
    }

    override fun getCount(): Int = Images.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view ==  `object` as RelativeLayout

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var image:ImageView
        var title:TextView
        var description:TextView
        inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        var view:View= inflater.inflate(R.layout.layout_slider,container,false)
        image=view.findViewById(R.id.sliderImage)
        title=view.findViewById(R.id.imageTitle)
        description=view.findViewById(R.id.imageDescription)
        image.setBackgroundResource(Images[position])
        title.text= Title[position]
        description.text= Description[position]
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
//        super.destroyItem(container, position, `object`)
    }

}