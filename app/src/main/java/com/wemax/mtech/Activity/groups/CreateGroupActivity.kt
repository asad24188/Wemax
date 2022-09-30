package com.wemax.mtech.Activity.groups

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.InviteFriendsAdapter
import com.wemax.mtech.Adapter.groups.CustomSpinnerAdapter
import com.wemax.mtech.Model.calendarReminder.NewReminderModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityCreateGroupBinding


class CreateGroupActivity : AppCompatActivity() {


    lateinit var binding: ActivityCreateGroupBinding
    val contextActivity = this@CreateGroupActivity
    lateinit var utils: Utilities
    var privacyStr = ""
    lateinit var adapterCustomSinner: CustomSpinnerAdapter
    var privacy_titlesList = arrayOf("Public", "Friends", "Only Me")
    var customSpinnerImagesList = intArrayOf(
        R.drawable.public_privacy,
        R.drawable.public_privacy,
        R.drawable.public_privacy
    )

    var listGroupMembers: ArrayList<NewReminderModel> = arrayListOf()
    lateinit var adapter: InviteFriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateGroupBinding.inflate(layoutInflater)
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
        getSpinnersValue()

        binding.recyclerInvite.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.recyclerInvite.adapter = InviteFriendsAdapter(contextActivity, listGroupMembers)
    }

    private fun getSpinnersValue() {


        binding.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
/*                    Toast.makeText(
                        contextActivity,
                        "You Select Position: " + position + " " + privacy_titlesList[position],
                        Toast.LENGTH_SHORT
                    ).show()*/
//                    privacyStr = parent.getItemAtPosition(position).toString()
                    privacyStr = privacy_titlesList[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

//                    category = "Category 1"
                    privacyStr = "Public"
                }
            }

        val customAdapter =
            CustomSpinnerAdapter(applicationContext, customSpinnerImagesList, privacy_titlesList)
        binding.spinnerCategory.adapter = customAdapter

    }
    private fun initRecyclerView() {
        listGroupMembers.add(
            NewReminderModel(
                "",
                R.drawable.ic_user_img2,
                resources.getString(R.string.cordelia_john),
                false,
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