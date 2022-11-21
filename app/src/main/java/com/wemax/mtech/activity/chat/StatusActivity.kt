package com.wemax.mtech.activity.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.Adapter.StatusAdapter
import com.wemax.mtech.Model.StatusModel
import com.wemax.mtech.databinding.ActivityStatusBinding

class StatusActivity : AppCompatActivity() {
    lateinit var binding: ActivityStatusBinding
    var listComments: ArrayList<StatusModel> = arrayListOf()
    lateinit var adapter: StatusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initRecyclerView()
        binding.rcv.layoutManager = LinearLayoutManager(this)
        adapter = StatusAdapter(this, listComments)
        binding.rcv.adapter = adapter
    }
    private fun initRecyclerView() {
        listComments.add(
            StatusModel(
                "Available"
            )
        )
        listComments.add(
            StatusModel(
                "Working"
            )
        )
        listComments.add(
            StatusModel(
                "Busy"
            )
        )


    }
}