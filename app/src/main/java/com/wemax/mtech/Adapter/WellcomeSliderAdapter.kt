package com.wemax.mtech.Adapter

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wemax.mtech.R
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.wemax.mtech.Model.WellcomeSliderModel
import java.util.ArrayList

class WellcomeSliderAdapter(
    private val context: Context,
    private val IMAGES_list: ArrayList<WellcomeSliderModel>
) :
    PagerAdapter() {
    private val inflater: LayoutInflater
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return IMAGES_list.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.item_slider_welcome_scr, view, false)!!

        val imageView = imageLayout
            .findViewById<View>(R.id.image) as ImageView
        val titlebold = imageLayout
            .findViewById<TextView>(R.id.titleBoldCaption) as TextView
        val caption = imageLayout
            .findViewById<TextView>(R.id.titleLightCaption) as TextView

//        imageView.setImageResource(IMAGES[position])
        val model = IMAGES_list.get(position)
        imageView.setImageResource(model.images)
        titlebold.text = model.boldTitle
        caption.text = model.caption

        view.addView(imageLayout, 0)
        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}
    override fun saveState(): Parcelable? {
        return null
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}