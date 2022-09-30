package com.wemax.mtech.Activity.groups

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.groups.GroupPostsAdapterInFragment
import com.wemax.mtech.Adapter.groups.GroupsAdapterInFragment
import com.wemax.mtech.Model.groups.GroupsModelInFragment
import com.wemax.mtech.Model.groups.SinglePostModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityMyProfileBinding
import java.util.ArrayList

class MyProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyProfileBinding
    val contextActivity = this@MyProfileActivity
    lateinit var utils: Utilities

    lateinit var listGroups: ArrayList<GroupsModelInFragment>
    lateinit var adapterGroups: GroupsAdapterInFragment

    var listGroupsPosts: ArrayList<SinglePostModel> = arrayListOf()
    lateinit var adapterGroupsPosts: GroupPostsAdapterInFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
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
        groupsRecyclerView()
        groupsPostRecycleView()
    }


    fun groupsRecyclerView() {
        binding.groupsRecyclerView.layoutManager =
            LinearLayoutManager(contextActivity, LinearLayoutManager.HORIZONTAL, false)
//        binding.groupsRecyclerView.setHasFixedSize(true)
        listGroups = arrayListOf<GroupsModelInFragment>()
        getGoupsInfoData()
        binding.groupsRecyclerView.adapter =
            GroupsAdapterInFragment(contextActivity, listGroups)
    }

    fun groupsPostRecycleView() {
        binding.prevoiusPostsRecycler.layoutManager = LinearLayoutManager(contextActivity)
//        listGroups = arrayListOf()
        getGroupsPosts()
        binding.prevoiusPostsRecycler.adapter =
            GroupPostsAdapterInFragment(contextActivity, listGroupsPosts)

    }

    fun getGoupsInfoData() {
        listGroups.add(
            GroupsModelInFragment(
                "",
                R.drawable.group_dp_1,
                resources.getString(R.string.nice_places_group_name),
                "1K",
                "60", "Public"
            )
        )

        listGroups.add(
            GroupsModelInFragment(
                "",
                R.drawable.group_dp_2,
                resources.getString(R.string.best_friends_group_name),
                "60",
                "2", "Private"
            )
        )
        listGroups.add(
            GroupsModelInFragment(
                "",
                R.drawable.group_dp_3,
                resources.getString(R.string.professional_barbers_group_name),
                "60",
                "2", "Private"
            )
        )
        listGroups.add(
            GroupsModelInFragment(
                "",
                R.drawable.group_dp_4,
                resources.getString(R.string.best_friends_group_name),
                "60",
                "2", "Private"
            )
        )
    }

    fun getGroupsPosts() {
        listGroupsPosts.add(
            SinglePostModel(
                "",
                "",
                "",
                resources.getString(R.string.professional_barbers_group_name),
                R.drawable.mygroup_image_3,
                resources.getString(R.string.cordelia_john),
                R.drawable.user1,
                R.drawable.group_dp_3,
                R.drawable.ic_friends_users,
                "23 min ago",
                resources.getString(R.string.post_caption3),
                "66",
                "2",
                "5",

                )
        )
        listGroupsPosts.add(
            SinglePostModel(
                "",
                "",
                "",
                resources.getString(R.string.best_friends_group_name),
                R.drawable.mygroup_image_2,
                resources.getString(R.string.cordelia_john),
                R.drawable.user2,
                R.drawable.group_dp_2,
                R.drawable.public_privacy,
                "2 hours ago",
                resources.getString(R.string.post_caption2),
                "6",
                "22",
                "5",

                )
        )
        listGroupsPosts.add(
            SinglePostModel(
                "",
                "",
                "",
                resources.getString(R.string.nice_places_group_name),
                R.drawable.mygroup_image_1,
                resources.getString(R.string.cordelia_john),
                R.drawable.user3,
                R.drawable.group_dp_1,
                R.drawable.ic_friends_users,
                "15 sec ago",
                resources.getString(R.string.post_caption2),
                "1K",
                "2",
                "35",

                )
        )
    }


}