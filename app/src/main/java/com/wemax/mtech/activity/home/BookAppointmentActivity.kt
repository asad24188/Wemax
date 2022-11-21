package com.wemax.mtech.activity.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.ChipsAdapter
import com.wemax.mtech.Model.ChipsTagsModel
import com.wemax.mtech.databinding.ActivityBookAppointmentBinding

class BookAppointmentActivity : AppCompatActivity() {
    var listChips: ArrayList<ChipsTagsModel> = arrayListOf()
    lateinit var utils: Utilities
    val contextActivity = this@BookAppointmentActivity
    lateinit var binding:ActivityBookAppointmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        utils = Utilities(contextActivity)
        chipsLayoutManager()
        clicks()
    }

    private fun clicks() {
        binding.backpress.setOnClickListener {
            finish()
            overridePendingTransition(0,0)
        }
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this,FilterActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
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
                "Hair Cut",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Nail Polish",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Pizza",
            )
        )

        listChips.add(
            ChipsTagsModel(
                "Hamburger",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Pasta",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Perks",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "cycling",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Running",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "scuba Diving",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Mixed Cardio",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Running",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "FootBall",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "ice-skating",
            )
        )


    }
}