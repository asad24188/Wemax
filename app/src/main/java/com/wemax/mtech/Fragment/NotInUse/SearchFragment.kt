package com.wemax.mtech.Fragment.NotInUse

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.RecentSearchAdapter
import com.wemax.mtech.Model.ChipsTagsModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    lateinit var newArrayList: ArrayList<ChipsTagsModel>
    private lateinit var mMap: GoogleMap
    lateinit var utils: Utilities
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        utils = Utilities(requireContext())
        initViews()
        return view
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
        binding.rcv.adapter = RecentSearchAdapter(requireContext(), newArrayList)
    }

    private fun getData() {
        newArrayList = arrayListOf()
        newArrayList.add(ChipsTagsModel("Gujranwala"))
        newArrayList.add(ChipsTagsModel("Lahore"))
        newArrayList.add(ChipsTagsModel("Islamabad"))
        newArrayList.add(ChipsTagsModel("Superior"))
        newArrayList.add(ChipsTagsModel("Peshawar"))
    }
//    private fun getMap() {
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        val mapFragment :SupportMapFragment=SupportMapFragment().childFragmentManager
//            .findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this@SearchFragment)
//    }
//     override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//        MapsInitializer.initialize(requireContext())
//        addCustomMarker()
//    }

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
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true
    }

    private fun getMarkerBitmapFromView(@DrawableRes resId: Int): Bitmap? {
        val customMarkerView: View =
            (getSystemService(requireContext(), javaClass) as LayoutInflater).inflate(
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