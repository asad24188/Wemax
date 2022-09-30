package com.wemax.mtech.Adapter

import android.content.Context
import android.net.Uri
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wemax.mtech.R
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import com.wemax.mtech.Model.VideosPlaceDetailsModel
import com.wemax.mtech.Model.WellcomeSliderModel
import java.util.ArrayList

class VideoPagerSliderAdapter(
    private val context: Context,
    private val VIDEO_list: ArrayList<VideosPlaceDetailsModel>
) :
    PagerAdapter() {
    private val inflater: LayoutInflater
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return VIDEO_list.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val videoViewLayoutInflator = inflater.inflate(R.layout.item_video_post_detail, view, false)!!

        val videoViewTestVideo = videoViewLayoutInflator
            .findViewById<View>(R.id.videoViewTestVideo) as VideoView
        val model = VIDEO_list.get(position)

        var uri: Uri = Uri.parse(model.videoUri)
//        videoViewTestVideo.setMediaController(MediaController(context));
        videoViewTestVideo.setVideoURI(uri)
        videoViewTestVideo.requestFocus()
        videoViewTestVideo.start()

        view.addView(videoViewLayoutInflator, 0)
        return videoViewLayoutInflator
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