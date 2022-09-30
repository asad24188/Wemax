package com.wemax.mtech.Activity.groups

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.groups.GroupMembersAdapter
import com.wemax.mtech.Adapter.groups.MyGroupsAdapter
import com.wemax.mtech.Model.groups.MyGroupsModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityMyGroupsBinding

class MyGroupsActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyGroupsBinding
    val contextActivity = this@MyGroupsActivity
    lateinit var utils: Utilities

    var listMyGroups: ArrayList<MyGroupsModel> = arrayListOf()
    lateinit var adapter: MyGroupsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyGroupsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()
    }

    private fun initViews() {
        utils = Utilities(contextActivity)
    }

    private fun onClicks() {
        binding.backpress.setOnClickListener { finish() }

        binding.myGroupsRecycler.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.myGroupsRecycler.adapter = MyGroupsAdapter(contextActivity, listMyGroups)

    }

    private fun initRecyclerView() {

        listMyGroups.add(
            MyGroupsModel(
                "",
                R.drawable.mygroup_image_1,
                resources.getString(R.string.nice_places_group_name),
                "34",
                "60"
            )
        )
        listMyGroups.add(
            MyGroupsModel(
                "",
                R.drawable.mygroup_image_2,
                resources.getString(R.string.best_friends_group_name),
                "12",
                "2"
            )
        )
        listMyGroups.add(
            MyGroupsModel(
                "",
                R.drawable.mygroup_image_3,
                resources.getString(R.string.professional_barbers_group_name),
                "73",
                "23"
            )
        )
        listMyGroups.add(
            MyGroupsModel(
                "",
                R.drawable.group_dp_4,
                resources.getString(R.string.nice_places_group_name),
                "43",
                "100+"
            )
        )
        listMyGroups.add(
            MyGroupsModel(
                "",
                R.drawable.group_dp_5,
                resources.getString(R.string.best_friends_group_name),
                "33",
                "56"
            )
        )
    }


}