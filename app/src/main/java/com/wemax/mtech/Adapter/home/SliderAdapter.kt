package com.wemax.mtech.Adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.wemax.mtech.Model.homeApi.*
import com.wemax.mtech.R
import com.wemax.mtech.utils.Constants

class SliderAdapter(val context: Context,
                    val action: String,
                    val topSectionService : ArrayList<TopSectionService>,
                    val mid_section_services: ArrayList<MidSectionService>,
                    val bottomSectionService : ArrayList<BottomSectionService>) : PagerAdapter() {

    lateinit var inflater:LayoutInflater
    override fun getCount(): Int {

        when (action){

            Constants.TOP_SLIDER -> { return topSectionService.size }

            Constants.MID_SLIDER -> { return mid_section_services.size }

            Constants.BOTTOM_SLIDER -> { return bottomSectionService.size }
        }
        return 0

    }

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

        when (action){

            Constants.TOP_SLIDER -> {

                val obj = topSectionService.get(position)
                Glide.with(context).load(obj.subcategory_image).into(image)
                title.text= obj.service_title
                description.text = obj.subcategory_name
            }

            Constants.MID_SLIDER -> {

                val obj = mid_section_services.get(position)
                Glide.with(context).load(obj.subcategory_image).into(image)
                title.text= obj.service_title
                description.text= obj.subcategory_name

            }

            Constants.BOTTOM_SLIDER -> {

                val obj = bottomSectionService.get(position)
                Glide.with(context).load(obj.subcategory_image).into(image)
                title.text= obj.service_title
                description.text= obj.subcategory_name
            }
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

}