package com.wemax.mtech.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.activity.groups.InviteFriends2Activity
import com.wemax.mtech.Adapter.ChipsAdapter
import com.wemax.mtech.Model.ChipsTagsModel
import com.wemax.mtech.databinding.ActivityHobbiesInterestBinding

class HobbiesInterestActivity : AppCompatActivity() {
    lateinit var binding: ActivityHobbiesInterestBinding
    val contextActivity = this@HobbiesInterestActivity
    lateinit var utils: Utilities

    private var listChips: ArrayList<ChipsTagsModel> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHobbiesInterestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClicks()
    }


    private fun initViews() {

        utils = Utilities(contextActivity)
    }

    private fun onClicks() {

        binding.arrowBack.setOnClickListener { finish() }
        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, InviteFriends2Activity::class.java))
        }
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, InviteFriends2Activity::class.java))
        }

        chipsLayoutManager()

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
        binding.rv.layoutManager = chipsLayoutManager


        initRecyclerView()
        binding.rv.adapter = ChipsAdapter(contextActivity, listChips)
    }

    private fun initRecyclerView() {
        listChips.add(
            ChipsTagsModel(
                "Walking",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "hiking",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "cycling",
            )
        )

        listChips.add(
            ChipsTagsModel(
                "High intensity Interval\ntraining",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "FootBall",
            )
        )
        listChips.add(
            ChipsTagsModel(
                "Tennis",
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