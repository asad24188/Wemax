package com.wemax.mtech.activity.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.ChipsAdapter
import com.wemax.mtech.Model.ChipsTagsModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityCreatePlaceToVisitBinding

class CreatePlaceToVisitActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreatePlaceToVisitBinding

    var category = ""
    val contextActivity = this@CreatePlaceToVisitActivity
    var selectedImage: ImageView? = null
    var listChips: ArrayList<ChipsTagsModel> = arrayListOf()
    lateinit var utils: Utilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePlaceToVisitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()
    }

    private fun initViews() {

        utils = Utilities(contextActivity)
    }

    private fun onClicks() {
        getSpinnersValue()
        chipsLayoutManager()
        binding.btncreate.setOnClickListener {
            startActivity(Intent(this, PlaceDetailsActivity::class.java))
        }

        binding.backpress.setOnClickListener { finish() }

        binding.ivAddImage.setOnClickListener {
            addImage()
        }
        binding.ivAddVideo.setOnClickListener {
            addVideo()
        }

    }


    fun addImage() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.item_upload_image, null)
        // Add the new row before the add field button.
        binding.parentLinearLayoutPhoto.addView(
            rowView,
            binding.parentLinearLayoutPhoto.childCount - 1
        )
        binding.parentLinearLayoutPhoto.isFocusable
        selectedImage = rowView.findViewById(R.id.number_edit_text)
//        selectImage(this@CreatePlaceToVisitActivity)
    }

    fun addVideo() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.item_upload_video, null)
        // Add the new row before the add field button.
        binding.parentLinearLayout.addView(rowView, binding.parentLinearLayout.childCount - 1)
        binding.parentLinearLayout.isFocusable
        selectedImage = rowView.findViewById(R.id.number_edit_text)
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


    private fun getSpinnersValue() {
        val list1_category = arrayOf(
            "Category 1",
            "Category 2",
            "Category 3",
        )
//        binding.spinnerCategory.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list1_category)
        binding.spinnerCategory.adapter =
            ArrayAdapter(this, R.layout.my_spinner_item, list1_category)
        binding.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
/*                Toast.makeText(
                    contextActivity,
                    parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT
                ).show()*/
                    category = parent.getItemAtPosition(position).toString()
//                    category = list1_category[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

//                    category = "Category 1"
                    category = "Select Event Category"
                }
            }


    }
}