package com.wemax.mtech.activity.groups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.groups.GroupPostsAdapterInActivity
import com.wemax.mtech.Adapter.groups.GroupPostsAdapterInFragment
import com.wemax.mtech.Model.groups.SinglePostModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityGroupPostsBinding
import java.util.ArrayList

class GroupPostsActivity : AppCompatActivity() {
    lateinit var binding: ActivityGroupPostsBinding
    val contextActivity = this@GroupPostsActivity
    lateinit var utils: Utilities


    var listGroupsPosts: ArrayList<SinglePostModel> = arrayListOf()
    lateinit var adapterGroupsPosts: GroupPostsAdapterInFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        onClicks()
    }

    private fun initViews() {
        utils = Utilities(contextActivity)
        groupsPostRecycleView()
    }

    private fun onClicks() {
        binding.backpress.setOnClickListener { finish() }
        binding.floatbtnLayout.setOnClickListener { startActivity(Intent(contextActivity, CreatePostActivity::class.java)) }

        binding.viewMembersButton.setOnClickListener {
            startActivity(Intent(contextActivity, GroupMembersActivity::class.java))
//            finish()
        }

    }

    fun groupsPostRecycleView() {
        binding.postRecyclerView.layoutManager = LinearLayoutManager(contextActivity)
//        listGroups = arrayListOf()
        getGroupsPosts()
        binding.postRecyclerView.adapter =
            GroupPostsAdapterInActivity(contextActivity, listGroupsPosts)

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