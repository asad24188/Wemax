package com.wemax.mtech.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.Adapter.AddressAdapter
import com.wemax.mtech.Model.AddressDataModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityAddressesBinding

class AddressesActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddressesBinding

    private lateinit var adapter: AddressAdapter
    private lateinit var list: ArrayList<AddressDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)


        list = ArrayList()
        list.add(
            AddressDataModel("8614 Mcclellan Rd New York, United States")
        )
        list.add(
            AddressDataModel("8614 Mcclellan Rd New York, United States")
        )

        binding.addressRecycler.layoutManager = LinearLayoutManager(this)
        adapter = AddressAdapter(this,list)
        binding.addressRecycler.adapter = adapter

    }
}