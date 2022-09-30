package com.wemax.mtech.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.Activity.home.event.EventDetailsActivity
import com.wemax.mtech.Adapter.InviteFriendsAdapter
import com.wemax.mtech.Model.calendarReminder.NewReminderModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityAddParticipantsBinding
import com.wemax.mtech.databinding.ActivityInviteFriendsBinding
//
class InviteFriendsActivity : AppCompatActivity() {
    lateinit var binding: ActivityInviteFriendsBinding
    val contextActivity = this@InviteFriendsActivity

    var listGroupMembers: ArrayList<NewReminderModel> = arrayListOf()
    lateinit var adapter: InviteFriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInviteFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        onClicks()

    }

    private fun onClicks() {
        binding.backpress.setOnClickListener { finish() }
        binding.recyclerInvite.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.recyclerInvite.adapter = InviteFriendsAdapter(contextActivity, listGroupMembers)

        binding.btnCreate.setOnClickListener {
            startActivity(Intent(contextActivity, EventDetailsActivity::class.java))
        }
    }

    private fun initRecyclerView() {
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img2,
                resources.getString(R.string.cordelia_john),
                true,
            )
        )
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img3,
                resources.getString(R.string.olive),
                true
            )
        )
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img1,
                resources.getString(R.string.william),
                false
            )
        )
    }
}