package com.wemax.mtech.Adapter.home.serviceDetails

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wemax.mtech.R
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import java.util.ArrayList

class ViewPagerSliderAdapter(private val context: Context, private val IMAGES: ArrayList<Int>) :
    PagerAdapter() {
    private val inflater: LayoutInflater
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return IMAGES.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.item_slider_service_detail, view, false)!!
        val imageView = imageLayout
            .findViewById<View>(R.id.image) as ImageView
        imageView.setImageResource(IMAGES[position])
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