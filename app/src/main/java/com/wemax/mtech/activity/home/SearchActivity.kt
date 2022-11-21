package com.wemax.mtech.activity.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.AdapterRecomendedFor
import com.wemax.mtech.Model.groups.PostModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivitySearchBinding
import java.util.ArrayList

class   SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    val contextActivity = this@SearchActivity
    lateinit var utils: Utilities
    private lateinit var adapterGrid: AdapterRecomendedFor
    private lateinit var listGrid: ArrayList<PostModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
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

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0,0)
    }
}