package com.wemax.mtech.activities.customMarker

import android.app.Activity
import android.view.View
import android.widget.ImageView
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.life360.activities.Maps.CustomMarkerModel
import com.google.android.gms.maps.model.Marker
import com.wemax.mtech.R

class CustomMarkerAdapter(private val context: Activity) : InfoWindowAdapter {
    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View? {
        val mInfoView: View = context.layoutInflater.inflate(R.layout.custom_marker_view, null)

     /*   var mInfoWindow: CustomMarkerModel? = marker?.tag as CustomMarkerModel?

        val locTitle = mInfoView.findViewById<View>(R.id.title) as TextView
        val locText = mInfoView.findViewById<View>(R.id.location) as TextView
        val locImage = mInfoView.findViewById<View>(R.id.loc_pin) as ImageView


//        Glide.with(context).load(mInfoWindow?.userImage).into(userImage)
        locTitle.text = mInfoWindow?.locTitle
        locText.text = mInfoWindow?.mLocationAddres
        locImage.setImageResource(mInfoWindow?.pinLocImage!!)
*/

        return mInfoView
    }
}