package com.wemax.mtech.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.Adapter.home.event.AcceptedAdapter
import com.wemax.mtech.Adapter.home.event.AcceptedAdapter2
import com.wemax.mtech.Model.AcceptedModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityAddParticipantsBinding

class AddParticipantsActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddParticipantsBinding
    val contextActivity = this@AddParticipantsActivity

    var listGroupMembers: ArrayList<AcceptedModel> = arrayListOf()
    lateinit var adapter: AcceptedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddParticipantsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        onClicks()

    }

    private fun onClicks() {
        binding.backpress.setOnClickListener { finish() }
        binding.recyclerInvite.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.recyclerInvite.adapter = AcceptedAdapter2(contextActivity, listGroupMembers,layoutInflater)
    }
    private fun initRecyclerView() {
        listGroupMembers.add(
            AcceptedModel(
                "",
                R.drawable.ic_user_img2,
                resources.getString(R.string.cordelia_john)
            )
        )
        listGroupMembers.add(
            AcceptedModel(
                "",
                R.drawable.ic_user_img3,
                resources.getString(R.string.olive)
            )
        )
        listGroupMembers.add(
            AcceptedModel(
                "",
                R.drawable.ic_user_img1,
                resources.getString(R.string.william)
            )
        )

    }
}