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
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Activity.auth.HomeActivity
import com.wemax.mtech.Activity.home.serviceDetails.SelectDateTimeActivity
import com.wemax.mtech.Adapter.ChipsAdapter
import com.wemax.mtech.Adapter.home.event.LabelsBringingListAdapter
import com.wemax.mtech.Adapter.home.event.UsersGoingAdapter
import com.wemax.mtech.Fragment.calendar.activities.CalendarEventActivity
import com.wemax.mtech.Model.ChipsTagsModel
import com.wemax.mtech.Model.home.event.BringingModelClass
import com.wemax.mtech.Model.home.event.UsersGoingModelClass
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityEventDetailsBinding

class EventDetailsActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var binding: ActivityEventDetailsBinding
    val contextActivity = this@EventDetailsActivity
    private lateinit var mMap: GoogleMap

    lateinit var adapterUsersGoing: UsersGoingAdapter
    var listUsersGoing: ArrayList<UsersGoingModelClass> = arrayListOf<UsersGoingModelClass>()

    lateinit var adapterBringingLables: LabelsBringingListAdapter
    var listBringingLables: ArrayList<BringingModelClass> = arrayListOf()

    var listChips: ArrayList<ChipsTagsModel> = arrayListOf()
    lateinit var utils: Utilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()
    }

    private fun initViews() {
        utils = Utilities(contextActivity)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(contextActivity)
    }

    private fun onClicks() {
//        binding.details.setOnClickListener { getDataDialog() }

        chipsLayoutManager()
        binding.details.setOnClickListener {
            startActivity(Intent(this, EventMembersActivity::class.java))

        }
        binding.viewMyCalendar.setOnClickListener {

            val fragment = "forum"
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("fragment", fragment)
            startActivity(intent)
            finishAffinity()
//            startActivity(Intent(this, CalendarEventActivity::class.java))


        }
        binding.btnconfirm.setOnClickListener {

            val fragment = "forum"
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("fragment", fragment)
            startActivity(intent)
            finishAffinity()


        }

        binding.back.setOnClickListener { finish() }

        binding.usersGoinfRecyclerView.layoutManager =
            LinearLayoutManager(contextActivity, LinearLayoutManager.HORIZONTAL, false)
        initUsersGoingRecyclerView()
        adapterUsersGoing = UsersGoingAdapter(contextActivity, listUsersGoing)
        binding.usersGoinfRecyclerView.adapter = adapterUsersGoing

        if (listUsersGoing.size > 5) {
            binding.remianingUsersCountingLayout.visibility = View.VISIBLE
            binding.remianingUsersCounting.text = (listUsersGoing.size - 5).toString() + "+"
        } else {
            binding.remianingUsersCountingLayout.visibility = View.GONE
        }

        binding.recyclerViewLablesBringingList.layoutManager =
            LinearLayoutManager(contextActivity)
        initBringingLablesRecyclerView()
        adapterBringingLables = LabelsBringingListAdapter(contextActivity, listBringingLables)
        binding.recyclerViewLablesBringingList.adapter = adapterBringingLables
    }


    private fun initUsersGoingRecyclerView() {
        listUsersGoing.add(UsersGoingModelClass("", R.drawable.user1))
        listUsersGoing.add(UsersGoingModelClass("", R.drawable.user2))
        listUsersGoing.add(UsersGoingModelClass("", R.drawable.user3))
        listUsersGoing.add(UsersGoingModelClass("", R.drawable.user1))
        listUsersGoing.add(UsersGoingModelClass("", R.drawable.user2))
        listUsersGoing.add(UsersGoingModelClass("", R.drawable.user3))
        listUsersGoing.add(UsersGoingModelClass("", R.drawable.user1))
        listUsersGoing.add(UsersGoingModelClass("", R.drawable.user2))
        listUsersGoing.add(UsersGoingModelClass("", R.drawable.user3))
    }

    private fun initBringingLablesRecyclerView() {
        listBringingLables.add(BringingModelClass("Cold Drink", "Asad"))
        listBringingLables.add(BringingModelClass("Large Pizza", "Kamran"))
        listBringingLables.add(BringingModelClass("Juice", "Waqar"))
        listBringingLables.add(BringingModelClass("Cigaret", "Faraz"))
    }

    private fun getDataDialog() {

        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.popup_persons_event_details, null)

        val goingPersons = view.findViewById<TextView>(R.id.noOfPeopleGoing)
        val btnCancel = view.findViewById<LinearLayout>(R.id.cancelImageLayout)

//        goingPersonsStr = goingPersons.text.toString()
        btnCancel.setOnClickListener {

            builder.dismiss()
        }


        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
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
}