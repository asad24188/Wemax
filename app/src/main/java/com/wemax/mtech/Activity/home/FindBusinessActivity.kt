package com.wemax.mtech.Activity.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.AdapterRecomendedFor
import com.wemax.mtech.Model.groups.PostModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityFindBusinessBinding
import java.util.ArrayList

class FindBusinessActivity : AppCompatActivity() {
    val contextActivity = this@FindBusinessActivity
    lateinit var utils: Utilities

    private lateinit var adapterGrid: AdapterRecomendedFor
    private lateinit var listGrid: ArrayList<PostModel>
    lateinit var binding:ActivityFindBusinessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFindBusinessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        initViews()
        onClicks()
    }
    private fun initViews() {
        utils = Utilities(contextActivity)
    }

    private fun onClicks() {
        binding.backpress.setOnClickListener{
            finish()
            overridePendingTransition(0,0)
        }
        listGrid = ArrayList()
        listGrid.add(
            PostModel(
                R.drawable.provider1,
                getString(R.string.post_title),
                getString(R.string.rating_text)
            )
        )
        listGrid.add(
            PostModel(
                R.drawable.provider2,
                getString(R.string.post_title1),
                getString(R.string.rating_text)
            )
        )
        listGrid.add(
            PostModel(
                R.drawable.provider1,
                getString(R.string.post_title),
                getString(R.string.rating_text)
            )
        )
        listGrid.add(
            PostModel(
                R.drawable.provider2,
                getString(R.string.post_title1),
                getString(R.string.rating_text)
            )
        )
        listGrid.add(
            PostModel(
                R.drawable.provider2,
                getString(R.string.post_title1),
                getString(R.string.rating_text)
            )
        )
        listGrid.add(
            PostModel(
                R.drawable.provider2,
                getString(R.string.post_title1),
                getString(R.string.rating_text)
            )
        )
        listGrid.add(
            PostModel(
                R.drawable.provider2,
                getString(R.string.post_title1),
                getString(R.string.rating_text)
            )
        )
        listGrid.add(
            PostModel(
                R.drawable.provider2,
                getString(R.string.post_title1),
                getString(R.string.rating_text)
            )
        )
        listGrid.add(
            PostModel(
                R.drawable.provider2,
                getString(R.string.post_title1),
                getString(R.string.rating_text)
            )
        )

        binding.recyclerRecomended.layoutManager = GridLayoutManager(this, 2)
        adapterGrid = AdapterRecomendedFor(this, listGrid)
        binding.recyclerRecomended.adapter = adapterGrid

        binding.floatbtnLayout.setOnClickListener {

            startActivity(Intent(contextActivity, CreatePlaceToVisitActivity::class.java))
        }

    }
}