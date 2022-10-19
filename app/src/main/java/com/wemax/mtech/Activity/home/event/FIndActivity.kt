package com.wemax.mtech.Activity.home.event

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Activity.home.BookAppointmentActivity
import com.wemax.mtech.Adapter.RecentSearchAdapter
import com.wemax.mtech.Model.ChipsTagsModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityFindBinding

class FIndActivity : AppCompatActivity() {

    lateinit var binding: ActivityFindBinding
    private lateinit var mMap: GoogleMap
    lateinit var utils: Utilities
    lateinit var newArrayList: ArrayList<ChipsTagsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindBinding.inflate(layoutInflater)
        setContentView(binding.root)
        utils = Utilities(this)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        initViews()

        binding.back.setOnClickListener {
            finish()

        }
    }

    private fun initViews() {

        recentSearches()
//        getMap()
    }

    private fun recentSearches() {
        binding.rcv.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL)
        binding.rcv.setHasFixedSize(true)
        newArrayList = arrayListOf()
        getData()
        binding.rcv.adapter = RecentSearchAdapter(this, newArrayList)
    }

    private fun getData() {
        newArrayList = arrayListOf()
        newArrayList.add(ChipsTagsModel("Gujranwala"))
        newArrayList.add(ChipsTagsModel("Lahore"))
        newArrayList.add(ChipsTagsModel("Islamabad"))
        newArrayList.add(ChipsTagsModel("Superior"))
        newArrayList.add(ChipsTagsModel("Peshawar"))
    }

    private fun addCustomMarker() {
        if (mMap == null) {
            return
        }
        //location 1
        val newLocation = LatLng(31.40993509630959, 73.06530713934792)
        // adding a marker on map with image from  drawable
        mMap.addMarker(
            MarkerOptions()
                .position(newLocation)
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.pin_icon)!!))
        )


//        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 15F))


        // get location button
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true
    }

    private fun getMarkerBitmapFromView(@DrawableRes resId: Int): Bitmap? {
        val customMarkerView: View =
            (ContextCompat.getSystemService(this, javaClass) as LayoutInflater).inflate(
                R.layout.custom_marker_view,
                null
            )
        val markerImageView: ImageView =
            customMarkerView.findViewById(R.id.loc_pin) as ImageView
        markerImageView.setImageResource(resId)
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        customMarkerView.layout(
            0,
            0,
            customMarkerView.getMeasuredWidth(),
            customMarkerView.getMeasuredHeight()
        )
        customMarkerView.buildDrawingCache()
        val returnedBitmap = Bitmap.createBitmap(
            customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(returnedBitmap)
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN)
        val drawable: Drawable = customMarkerView.getBackground()
        if (drawable != null) drawable.draw(canvas)
        customMarkerView.draw(canvas)
        return returnedBitmap
    }

}