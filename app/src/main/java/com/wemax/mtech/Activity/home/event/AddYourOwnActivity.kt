package com.wemax.mtech.Activity.home.event

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Activity.home.CreatePlaceToVisitActivity
import com.wemax.mtech.Adapter.AdapterRecomendedFor
import com.wemax.mtech.Adapter.home.event.AddYourOwnAdapter
import com.wemax.mtech.Model.groups.PostModel
import com.wemax.mtech.Model.home.event.AddYourOwnModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityAddYourOwnBinding
import java.util.ArrayList

class AddYourOwnActivity : AppCompatActivity() {
    val contextActivity = this@AddYourOwnActivity
    lateinit var utils: Utilities
    private lateinit var adapterGrid: AddYourOwnAdapter
    private lateinit var listGrid: ArrayList<AddYourOwnModel>
    lateinit var binding:ActivityAddYourOwnBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddYourOwnBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        utils = Utilities(contextActivity)
        onClicks()
    }
    private fun onClicks() {
        binding.backpress.setOnClickListener{
            finish()
            overridePendingTransition(0,0)
        }
        listGrid = ArrayList()
        listGrid.add(AddYourOwnModel("Pizza",))
        listGrid.add(AddYourOwnModel("Salad",))
        listGrid.add(AddYourOwnModel("Beers",))
        listGrid.add(AddYourOwnModel("Vegetables",))
        listGrid.add(AddYourOwnModel("Apples",))
        listGrid.add(AddYourOwnModel("Banana",))
        listGrid.add(AddYourOwnModel("Coca Cola",))
        listGrid.add(AddYourOwnModel("Steaks",))
        listGrid.add(AddYourOwnModel("Napkins"))

        binding.recyclerRecomended.layoutManager = LinearLayoutManager(this)
        adapterGrid = AddYourOwnAdapter(this, listGrid)
        binding.recyclerRecomended.adapter = adapterGrid

        binding.floatbtnLayout.setOnClickListener {
            startActivity(Intent(contextActivity, CreatePlaceToVisitActivity::class.java))
        }

    }
}