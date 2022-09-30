package com.wemax.mtech.Activity.groups

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.wemax.mtech.Adapter.AdapterRecomendedFor
import com.wemax.mtech.Adapter.groups.FindEventsAdapter
import com.wemax.mtech.Model.groups.PostModel
import com.wemax.mtech.Model.home.HotEventsPostModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityFindEventsBinding
import java.util.ArrayList

class FindEventsActivity : AppCompatActivity() {
    lateinit var binding: ActivityFindEventsBinding
    val contextAvtivity = this@FindEventsActivity

    lateinit var newArrayListHotEvents: ArrayList<HotEventsPostModel>

    private lateinit var adapterGrid: AdapterRecomendedFor
    private lateinit var listGrid: ArrayList<PostModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()
    }

    private fun initViews() {
    }

    private fun onClicks() {

        binding.backpress.setOnClickListener {
            finish()
        }
        hotEvents()
    }

    private fun hotEvents() {



        binding.rcvHotEvents.layoutManager = GridLayoutManager(this, 2)
        binding.rcvHotEvents.setHasFixedSize(true)
        newArrayListHotEvents = arrayListOf()
        getHotEventsData()
        binding.rcvHotEvents.adapter = FindEventsAdapter(contextAvtivity, newArrayListHotEvents)

    }

    private fun getHotEventsData() {
        newArrayListHotEvents = arrayListOf()
        newArrayListHotEvents.add(
            HotEventsPostModel(
                R.drawable.provider1, getString(R.string.post_title),
                getString(R.string.rating_text),
                getString(R.string._2_km_away)
            )
        )
        newArrayListHotEvents.add(
            HotEventsPostModel(
                R.drawable.provider1, getString(R.string.post_title),
                getString(R.string.rating_text),
                getString(R.string._2_km_away)
            )
        )
        newArrayListHotEvents.add(
            HotEventsPostModel(
                R.drawable.provider1, getString(R.string.post_title),
                getString(R.string.rating_text),
                getString(R.string._2_km_away)
            )
        )
        newArrayListHotEvents.add(
            HotEventsPostModel(
                R.drawable.provider1, getString(R.string.post_title),
                getString(R.string.rating_text),
                getString(R.string._2_km_away)
            )
        )
    }


}