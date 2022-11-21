package com.wemax.mtech.activity.home

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.remindrobort.app.utils.Utilities
import com.viewpagerindicator.CirclePageIndicator
import com.wemax.mtech.Adapter.ChipsAdapter
import com.wemax.mtech.Adapter.NewReminderAdapter
import com.wemax.mtech.Adapter.VideoPagerSliderAdapter
import com.wemax.mtech.Adapter.home.serviceDetails.FindSomeThingAdapter
import com.wemax.mtech.Adapter.home.serviceDetails.ServiceReviewsAdapter
import com.wemax.mtech.Model.ChipsTagsModel
import com.wemax.mtech.Model.VideosPlaceDetailsModel
import com.wemax.mtech.Model.calendarReminder.NewReminderModel
import com.wemax.mtech.Model.groups.PostModel
import com.wemax.mtech.Model.serviceDetailsModel.ServicesReviewsModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityPlaceDetailsBinding


class PlaceDetailsActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var newArrayList: java.util.ArrayList<PostModel>
    lateinit var adapterReviewsSerview: ServiceReviewsAdapter
    val listReviewSerives: ArrayList<ServicesReviewsModel> = arrayListOf()
    var listGroupMembers: ArrayList<NewReminderModel> = arrayListOf()
    lateinit var binding: ActivityPlaceDetailsBinding
    val contextActivity = this@PlaceDetailsActivity
    lateinit var utils: Utilities

    var position = 0
    private var currentPage = 0

    private var sliderVideoArrayList: ArrayList<VideosPlaceDetailsModel> = arrayListOf()
    private var data_Videos: ArrayList<VideosPlaceDetailsModel> = arrayListOf()


    private var videoImagesArray = arrayListOf<Int>()
    lateinit var indicator: CirclePageIndicator
    lateinit var mPager: ViewPager

    private lateinit var mMap: GoogleMap


    var listChips: ArrayList<ChipsTagsModel> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClicks()

/*        val chipGroup = ChipGroup(binding.parent.getContext())

        val genres = arrayOf("Thriller", "Comedy", "Adventure")
        for (genre in genres) {
            val chip = Chip(binding.parent.getContext())
            chip.text = genre
            chipGroup.addView(chip)
        }*/
    }

    private fun initViews() {

        utils = Utilities(contextActivity)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(contextActivity)
    }

    private fun onClicks() {
        binding.back.setOnClickListener { finish() }

//        testVideo()
        videoCode()


        chipsLayoutManager()
        binding.recyclerFriendshared.layoutManager = LinearLayoutManager(contextActivity)
        data()
        binding.recyclerFriendshared.adapter = NewReminderAdapter(contextActivity, listGroupMembers)

        initReviewsServices()
        binding.servicesReviewsRecyclerView.layoutManager = LinearLayoutManager(contextActivity)
        adapterReviewsSerview = ServiceReviewsAdapter(contextActivity, listReviewSerives)
        binding.servicesReviewsRecyclerView.adapter = adapterReviewsSerview

        binding.findBusinessesRecyclerView.layoutManager =
            LinearLayoutManager(contextActivity, LinearLayoutManager.HORIZONTAL, false)
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

    private fun data() {
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img2,
                resources.getString(R.string.cordelia_john),
                false,
            )
        )
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img3,
                resources.getString(R.string.olive),
                true
            )
        )
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img1,
                resources.getString(R.string.william),
                false
            )
        )
    }

    private fun chipsLayoutManager() {

        // ref link : https://github.com/BelooS/ChipsLayoutManager

        val chipsLayoutManager =
            ChipsLayoutManager.newBuilder(contextActivity)
                //set vertical gravity for all items in a row. Default = Gravity.CENTER_VERTICAL
                .setChildGravity(Gravity.TOP)
                //whether RecyclerView can scroll. TRUE by default
                .setScrollingEnabled(true)
                //set maximum views count in a particular row
                .setMaxViewsInRow(3)
                //set gravity resolver where you can determine gravity for item in position.
                //This method have priority over previous one
                .setGravityResolver { Gravity.CENTER }
                //you are able to break row due to your conditions. Row breaker should return true for that views
                .setRowBreaker { position -> position == 6 || position == 11 || position == 2 }
                //a layoutOrientation of layout manager, could be VERTICAL OR HORIZONTAL. HORIZONTAL by default
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                // row strategy for views in completed row, could be STRATEGY_DEFAULT, STRATEGY_FILL_VIEW,
                //STRATEGY_FILL_SPACE or STRATEGY_CENTER
                .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
                // whether strategy is applied to last row. FALSE by default
//                .withLastRow(true)
                .build()
        binding.rv.setLayoutManager(chipsLayoutManager)


//        binding.recyclerAccepted.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.rv.adapter = ChipsAdapter(contextActivity, listChips)
//        ViewCompat.setLayoutDirection(binding.rv, ViewCompat.LAYOUT_DIRECTION_RTL);
    }

    private fun initRecyclerView() {
        listChips.add(
            ChipsTagsModel(
                "Friends",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Couple",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "18+",
            )
        )

        listChips.add(
            ChipsTagsModel(
                "Under 18",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Adventurous",
            )
        )


    }

    private fun videoCode() {
        // add data in list

        data_Videos.add(
            VideosPlaceDetailsModel(
                "http://techslides.com/demos/sample-videos/small.mp4"
            )
        )

        data_Videos.add(
            VideosPlaceDetailsModel(
                "http://techslides.com/demos/sample-videos/small.webm"
            )
        )

        data_Videos.add(
            VideosPlaceDetailsModel(
                "http://techslides.com/demos/sample-videos/small.mp4"
            )
        )

        data_Videos.add(
            VideosPlaceDetailsModel(
                "http://techslides.com/demos/sample-videos/small.webm"
            )
        )

        data_Videos.add(
            VideosPlaceDetailsModel(
                "http://techslides.com/demos/sample-videos/small.mp4"
            )
        )



        for (i in data_Videos.indices) sliderVideoArrayList.add(
            data_Videos.get(i)
        )

        mPager =
            findViewById<View>(R.id.pager) as ViewPager

        indicator = findViewById<View>(R.id.indicator) as CirclePageIndicator

        mPager.setAdapter(
            VideoPagerSliderAdapter(contextActivity, sliderVideoArrayList)
        )

        getCirclePagerIndicator()
    }


    private fun getCirclePagerIndicator() {
        indicator.setViewPager(mPager)
        val density = resources.displayMetrics.density
        indicator.radius = 5 * density

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
                binding.sliderCurrentPosition.text =
                    (currentPage + 1).toString() + "/" + videoImagesArray.size
/*
                Toast.makeText(contextActivity, "" + (position + 1), Toast.LENGTH_SHORT)
                    .show()
*/
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }


    private fun testVideo() {

        var video_url1_mp4: String = "http://techslides.com/demos/sample-videos/small.mp4"
        var video_url2_webm: String = "http://techslides.com/demos/sample-videos/small.webm"

        var uri: Uri = Uri.parse(video_url1_mp4)
//        binding.videoViewTestVideo.setMediaController(MediaController(this));
        binding.videoViewTestVideo.setVideoURI(uri)
        binding.videoViewTestVideo.requestFocus()
        binding.videoViewTestVideo.start()
/*        binding.videoViewTestVideoFull.setActivity(this)

        val videoUri = Uri.parse(video_url1_mp4)
        try {
            binding.videoViewTestVideo.setVideoURI(videoUri)
        } catch (e: IOException) {
            e.printStackTrace()
        }*/
/*
        var video_url3_ogv: String =
            "http://techslides.com/demos/sample-videos/small.ogv"  // this format is not palyable
        var video_url4_3gp: String =
            "http://techslides.com/demos/sample-videos/small.3gp"  // this video size is not good
        var video_url5_flv: String =
            "http://techslides.com/demos/sample-videos/small.flv"  // this format is not playable

        var progressDialog: ProgressDialog? = null
        var mediaControls: MediaController? = null


        if (mediaControls == null) {
            mediaControls = MediaController(this)
        }

        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Buffering Video")
        progressDialog!!.setMessage("Loading...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()

        try {
            val video = Uri.parse(video_url1_mp4)
            binding.videoViewTestVideo.setVideoURI(video)
            binding.videoViewTestVideo.setMediaController(mediaControls)
        } catch (e: Exception) {
            Log.e("Error", e.message!!)
            e.printStackTrace()
        }

        binding.videoViewTestVideo.requestFocus()
        binding.videoViewTestVideo.setOnPreparedListener(OnPreparedListener
        // Close the progress bar and play the video
        {
            progressDialog!!.dismiss()
            binding.videoViewTestVideo.seekTo(position)
            if (position === 0) {
                binding.videoViewTestVideo.start()
            } else {
                binding.videoViewTestVideo.pause()
            }
        })*/
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("Position", binding.videoViewTestVideo.getCurrentPosition())
        binding.videoViewTestVideo.pause()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        position = savedInstanceState.getInt("Position")
        binding.videoViewTestVideo.seekTo(position)
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
}