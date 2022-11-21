package com.wemax.mtech

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.remindrobort.app.utils.Utilities
import com.viewpagerindicator.CirclePageIndicator
import com.wemax.mtech.databinding.ActivityServiceDetialsBinding
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.GridLayoutManager
import com.wemax.mtech.activity.auth.HomeActivity
import com.wemax.mtech.activity.home.serviceDetails.SelectDateTimeActivity
import com.wemax.mtech.Adapter.home.serviceDetails.*
import com.wemax.mtech.Model.groups.PostModel
import com.wemax.mtech.Model.home.serviceDetails.DateTimeModel
import com.wemax.mtech.Model.serviceDetailsModel.ServicesMoreModel
import com.wemax.mtech.Model.serviceDetailsModel.ServicesReviewsModel


class ServiceDetialsActivity : AppCompatActivity(), OnMapReadyCallback {
//class ServiceDetialsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    lateinit var binding: ActivityServiceDetialsBinding
    val contextActivity = this@ServiceDetialsActivity
    private lateinit var utils: Utilities

    private lateinit var mMap: GoogleMap

    lateinit var adapterServicesMore: ServicesMoreAdapter
    var listServicesMore: ArrayList<ServicesMoreModel> = arrayListOf()
    var dateTimeList: ArrayList<DateTimeModel> = arrayListOf()

    lateinit var adapterReviewsSerview: ServiceReviewsAdapter
    val listReviewSerives: ArrayList<ServicesReviewsModel> = arrayListOf()

    // link to help https://github.com/sonusurender/Image_Slider_Demo
    lateinit var mPager: ViewPager
    private var currentPage = 0
    private var IMAGES =
        arrayOf<Int>(R.drawable.slider_image1, R.drawable.slider_image2, R.drawable.slider_image3)
    private var sliderImagesArray = arrayListOf<Int>()
    lateinit var indicator: CirclePageIndicator


    lateinit var newArrayList: java.util.ArrayList<PostModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceDetialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        utils = Utilities(contextActivity)
//        utils.statusBarTextColorVisibility(window)
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)


        initViews()
//        utils.setWhiteBars(contextActivity)
        onClicks()
    }

    private fun initViews() {

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(contextActivity)

        initServicesMoreRecyclerView()
        binding.servicesMoreRecyclerView.layoutManager = LinearLayoutManager(contextActivity)
        adapterServicesMore = ServicesMoreAdapter(contextActivity, listServicesMore)
        binding.servicesMoreRecyclerView.adapter = adapterServicesMore

        initReviewsServices()
        binding.servicesReviewsRecyclerView.layoutManager = LinearLayoutManager(contextActivity)
        adapterReviewsSerview = ServiceReviewsAdapter(contextActivity, listReviewSerives)
        binding.servicesReviewsRecyclerView.adapter = adapterReviewsSerview

        binding.availableAppointment.layoutManager=
            GridLayoutManager(this,2)
        binding.availableAppointment.setHasFixedSize(true)
        dateTimeList= arrayListOf()
        getDate()
        binding.availableAppointment.adapter=DateTimeAdapter(this,dateTimeList,layoutInflater)

        binding.viewMyCalendar.setOnClickListener {
            val fragment = "forum"
            val intent = Intent(this,HomeActivity::class.java)
            intent.putExtra("fragment", fragment)
            startActivity(intent)
            finishAffinity()
            overridePendingTransition(0,0)
        }
        binding.btn.setOnClickListener {
            startActivity(Intent(this, SelectDateTimeActivity::class.java))

        }

        findBusinessAdapter()

        sliderCode()
    }

    private fun getDate() {
        dateTimeList= arrayListOf()
        dateTimeList.add(DateTimeModel("July 5","10:30 -11:00 Am"))
        dateTimeList.add(DateTimeModel("July 5","10:30 -11:00 Am"))
        dateTimeList.add(DateTimeModel("July 5","10:30 -11:00 Am"))
        dateTimeList.add(DateTimeModel("July 5","10:30 -11:00 Am"))
        dateTimeList.add(DateTimeModel("July 5","10:30 -11:00 Am"))
        dateTimeList.add(DateTimeModel("July 5","10:30 -11:00 Am"))
    }


    private fun findBusinessAdapter() {
//        binding.findBusinessesRecyclerView.layoutManager = GridLayoutManager(contextActivity, 2)
        binding.findBusinessesRecyclerView.layoutManager =   LinearLayoutManager(contextActivity, LinearLayoutManager.HORIZONTAL, false)
//        binding.findBusinessesRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<PostModel>()
        getPostData()
        binding.findBusinessesRecyclerView.adapter =
            FindSomeThingAdapter(contextActivity, newArrayList)
    }

    private fun getPostData() {
        newArrayList = arrayListOf()
        newArrayList.add(
            PostModel(
                R.drawable.provider1, getString(R.string.post_title),
                getString(R.string.rating_text)
            )
        )
        newArrayList.add(
            PostModel(
                R.drawable.provider1, getString(R.string.post_title),
                getString(R.string.rating_text)
            )
        )
        newArrayList.add(
            PostModel(
                R.drawable.provider1, getString(R.string.post_title),
                getString(R.string.rating_text)
            )
        )
        newArrayList.add(
            PostModel(
                R.drawable.provider1, getString(R.string.post_title),
                getString(R.string.rating_text)
            )
        )
    }


    private fun sliderCode() {
        for (i in IMAGES.indices) sliderImagesArray.add(
            IMAGES.get(i)
        )

        mPager =
            findViewById<View>(R.id.pager) as ViewPager
        indicator = findViewById<View>(R.id.indicator) as CirclePageIndicator

        mPager.setAdapter(
            ViewPagerSliderAdapter(contextActivity, sliderImagesArray)
        )


        getCirclePagerIndicator()
    }


    private fun getCirclePagerIndicator() {
        indicator.setViewPager(mPager)
        val density = resources.displayMetrics.density
        indicator.radius = 5 * density

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
                binding.sliderCurrentPosition.text =
                    (currentPage + 1).toString() + "/" + sliderImagesArray.size
/*
                Toast.makeText(contextActivity, "" + (position + 1), Toast.LENGTH_SHORT)
                    .show()
*/
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }


    private fun initServicesMoreRecyclerView() {
        listServicesMore.add(
            ServicesMoreModel(
                "",
                R.drawable.hair_style_image,
                getString(R.string.hair_style),
                getString(R.string.service_time_30_min),
                getString(R.string.service_cost_10)
            )
        )
        listServicesMore.add(
            ServicesMoreModel(
                "",
                R.drawable.hair_cut_imaga,
                getString(R.string.hair_cut),
                getString(R.string.service_time_1_hour),
                getString(R.string.service_cost_100)
            )
        )
        listServicesMore.add(
            ServicesMoreModel(
                "",
                R.drawable.nail_polish_image,
                getString(R.string.nail_polish_styles),
                getString(R.string.service_time_30_min),
                getString(R.string.service_cost_10)
            )
        )
        listServicesMore.add(
            ServicesMoreModel(
                "",
                R.drawable.nail_polish_remover,
                getString(R.string.nail_polish_removal),
                getString(R.string.service_time_15_min),
                getString(R.string.service_cost_5)
            )
        )
        /* listServicesMore.add(
             ServicesMoreModel(
                 "",
                 R.drawable.hair_style_image,
                 getString(R.string.hair_style),
                 getString(R.string.service_time_30_min),
                 getString(R.string.service_cost_10)
             )
         )*/
    }

    private fun initReviewsServices() {
        listReviewSerives.add(
            ServicesReviewsModel(
                "",
                R.drawable.user_image,
                getString(R.string.userName),
                getString(R.string.review_day_and_date),
                getString(R.string.review_text),
                getString(R.string.service_rating_value_details_activity)
            )
        )
        listReviewSerives.add(
            ServicesReviewsModel(
                "",
                R.drawable.user_image,
                getString(R.string.userName),
                getString(R.string.review_day_and_date),
                getString(R.string.review_text),
                "3.7"
            )
        )

        listReviewSerives.add(
            ServicesReviewsModel(
                "",
                R.drawable.user_image,
                getString(R.string.userName),
                getString(R.string.review_day_and_date),
                getString(R.string.review_text),
                "2.5"
            )
        )
        /* listReviewSerives.add(
             ServicesReviewsModel(
                 "",
                 R.drawable.user_image,
                 getString(R.string.userName),
                 getString(R.string.review_day_and_date),
                 getString(R.string.review_text),
                 getString(R.string.service_rating_value_details_activity)
             )
         )*/

    }

    private fun onClicks() {
        binding.back.setOnClickListener { finish() }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        MapsInitializer.initialize(this);
        addCustomMarker();


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

/*        mMap.addMarker(
            MarkerOptions()
                .position(newLocation).icon(
                    resizeBitmap(
                        R.drawable.pin_icon.toString(),
                        72,
                        64
                    )?.let {
                        BitmapDescriptorFactory.fromBitmap(
                            it
                        )
                    }
                )
        )*/

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 15F))

/*        mMap.addCircle(
            CircleOptions()
                .center(newLocation) // cneter should be latlong object which is my loction
                .radius(100.0) // 500meters
                .strokeWidth(1f) // 1f = weight of stroke/border
                .strokeColor(Color.BLUE)
                .fillColor(resources.getColor(R.color.radius_color))
//                .fillColor(Color.argb(70, 100, 140, 230))
        )*/

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

    fun resizeBitmap(drawableName: String?, width: Int, height: Int): Bitmap? {
        val imageBitmap = BitmapFactory.decodeResource(
            resources, resources.getIdentifier(
                drawableName, R.drawable.pin_icon.toString(),
                packageName
            )
        )
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false)
    }

    private fun getMarkerBitmapFromView(@DrawableRes resId: Int): Bitmap? {
        val customMarkerView: View =
            (getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
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


    fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        // below line is use to generate a drawable.
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        // below line is use to set bounds to our vector drawable.
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        // below line is use to create a bitmap for our
        // drawable which we have added.
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // below line is use to add bitmap in our canvas.
        val canvas = Canvas(bitmap)

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas)

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


/*    override fun onLocationChanged(location: Location) {
        var latitude: Double = location.latitude
        var longitude: Double = location.longitude

        val newLocation: LatLng = LatLng(latitude, longitude)
        mMap.addMarker(
            MarkerOptions().position(newLocation).title("Marker in your Current Location")
        ) // setting a markerto our new location
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation)) // puttig a new loction in (), if we change our location or want that app detect our location by default

//        mMap.uiSettings.isZoomControlsEnabled = true
    }*/

}