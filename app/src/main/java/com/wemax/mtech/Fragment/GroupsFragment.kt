package com.wemax.mtech.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Activity.groups.FindEventsActivity
import com.wemax.mtech.Activity.groups.CreateGroupActivity
import com.wemax.mtech.Activity.groups.MyGroupsActivity
import com.wemax.mtech.Adapter.groups.GroupPostsAdapterInFragment
import com.wemax.mtech.Adapter.groups.GroupsAdapterInFragment
import com.wemax.mtech.Model.groups.GroupsModelInFragment
import com.wemax.mtech.Model.groups.SinglePostModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.FragmentGroupsBinding
import java.util.ArrayList

class GroupsFragment : Fragment() {

    lateinit var utils: Utilities
    lateinit var binding: FragmentGroupsBinding

    lateinit var listGroups: ArrayList<GroupsModelInFragment>
    lateinit var adapterGroups: GroupsAdapterInFragment

    var listGroupsPosts: ArrayList<SinglePostModel> = arrayListOf()
    lateinit var adapterGroupsPosts: GroupPostsAdapterInFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGroupsBinding.inflate(inflater, container, false)
        initViews()
        onClicks()
        return binding.root
    }

    private fun initViews() {
        utils = context?.let { Utilities(it) }!!
        binding.myGroups.setOnClickListener {
            startActivity(Intent(context, MyGroupsActivity::class.java))
        }
        groupsRecyclerView()
        groupsNewPostRecycleView()
        groupsPostRecycleView()
    }

    private fun onClicks() {
        binding.floatbtnLayout.setOnClickListener {
            startActivity(Intent(context, CreateGroupActivity::class.java))
        }
        binding.findEvents.setOnClickListener {
            startActivity(Intent(context, FindEventsActivity::class.java))
        }
    }

    private fun groupsRecyclerView() {
        binding.groupsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        binding.groupsRecyclerView.setHasFixedSize(true)
        listGroups = arrayListOf<GroupsModelInFragment>()
        getGoupsInfoData()
        binding.groupsRecyclerView.adapter =
            GroupsAdapterInFragment(requireContext(), listGroups)
    }

    fun groupsNewPostRecycleView() {
        binding.newPostsForYouRecycler.layoutManager = LinearLayoutManager(context)
//        listGroups = arrayListOf()
        getNewGroupsPosts()
        binding.newPostsForYouRecycler.adapter =
            GroupPostsAdapterInFragment(requireContext(), listGroupsPosts)

    }

    fun groupsPostRecycleView() {
        binding.prevoiusPostsRecycler.layoutManager = LinearLayoutManager(context)
//        listGroups = arrayListOf()
        getGroupsPosts()
        binding.prevoiusPostsRecycler.adapter =
            GroupPostsAdapterInFragment(requireContext(), listGroupsPosts)

    }

    private fun getGoupsInfoData() {
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

    fun getNewGroupsPosts() {
        listGroupsPosts.add(
            SinglePostModel(
                "",
                "",
                "",
                resources.getString(R.string.professional_barbers_group_name),
                R.drawable.mygroup_image_1,
                resources.getString(R.string.cordelia_john),
                R.drawable.user1,
                R.drawable.group_dp_1,
                R.drawable.ic_friends_users,
                "23 min ago",
                resources.getString(R.string.post_caption3),
                "66",
                "2",
                "5",

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