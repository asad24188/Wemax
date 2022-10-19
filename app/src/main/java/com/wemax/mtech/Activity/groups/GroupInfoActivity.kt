package com.wemax.mtech.Activity.groups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Activity.AddParticipantsActivity
import com.wemax.mtech.Adapter.groups.AddedFriendsAdapter
import com.wemax.mtech.Adapter.groups.MyGroupsAdapter
import com.wemax.mtech.Adapter.groups.SelectFriendsAdapter
import com.wemax.mtech.Model.groups.AddedFriendsModel
import com.wemax.mtech.Model.groups.SelectFriendsModel
import com.wemax.mtech.Model.home.event.LabelsModelClass
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityGroupInfoBinding

class GroupInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityGroupInfoBinding
    val contextActivity = this@GroupInfoActivity
    lateinit var utils: Utilities

    var listMyFriends: ArrayList<SelectFriendsModel> = arrayListOf()
    lateinit var adapter: MyGroupsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        initViews()
        onClicks()
    }

    private fun initViews() {
        utils = Utilities(contextActivity)
    }

    private fun onClicks() {
        binding.myFriendsRecycler.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.myFriendsRecycler.adapter = SelectFriendsAdapter(contextActivity, listMyFriends,layoutInflater)

        binding.addParticipentsLayout.setOnClickListener {
            startActivity(Intent(contextActivity, AddParticipantsActivity::class.java))
        }

        binding.muteChatLayout.setOnClickListener {
            getDataDialog()
        }
        binding.backpress.setOnClickListener {
            finish()
            overridePendingTransition(0,0)
        }
    }

    private fun getDataDialog() {

        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.popup_mute_notifications, null)

        val cancelbtn = view.findViewById<TextView>(R.id.cancel)
        val okBtn = view.findViewById<TextView>(R.id.ok)
        val radio_group = view.findViewById<RadioGroup>(R.id.radio_groupTime)
      /*  var selectedTime = ""
        lateinit var selectedRadio: RadioButton
        val hours8RadioBtn = view.findViewById<RadioButton>(R.id.hours8)
        val week1RadioBtn = view.findViewById<RadioButton>(R.id.week1)
        val year1RadioBtn = view.findViewById<RadioButton>(R.id.year1)
*/


/*        radio_group.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            Toast.makeText(
                applicationContext, " On checked change :" +
                        " ${radio.text}",
                Toast.LENGTH_SHORT
            ).show()
        }*/
/*

        hours8RadioBtn.setOnClickListener {
            val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
            Toast.makeText(
                applicationContext, "On click : ${radio.text}",
                Toast.LENGTH_SHORT
            ).show()
        }

        week1RadioBtn.setOnClickListener {
            val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
            Toast.makeText(
                applicationContext, "On click : ${radio.text}",
                Toast.LENGTH_SHORT
            ).show()
        }

        year1RadioBtn.setOnClickListener {
            val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
            Toast.makeText(
                applicationContext, "On click : ${radio.text}",
                Toast.LENGTH_SHORT
            ).show()
        }
*/

        okBtn.setOnClickListener {
            // Get the checked radio button id from radio group
           /* var id: Int = radio_group.checkedRadioButtonId
            if (id != -1) { // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio: RadioButton = findViewById(id)
                Toast.makeText(
                    applicationContext, "On button click :" +
                            " ${radio.text}",
                    Toast.LENGTH_SHORT
                ).show()
                builder.dismiss()
            } else {
                // If no radio button checked in this radio group
                Toast.makeText(
                    applicationContext, "On button click :" +
                            " Nothing selected",
                    Toast.LENGTH_SHORT
                ).show()
            }*/

            builder.dismiss()
        }

        cancelbtn.setOnClickListener {
            builder.dismiss()
        }

        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }

/*
    // Get the selected radio button text using radio button on click listener
    fun radio_button_click(view: View){
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
        Toast.makeText(applicationContext,"On click : ${radio.text}",
            Toast.LENGTH_SHORT).show()
    }
*/

    private fun initRecyclerView() {

        listMyFriends.add(
            SelectFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user1,
                false
            )
        )
        listMyFriends.add(
            SelectFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user2,
                true
            )
        )
        listMyFriends.add(
            SelectFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user3,
                false
            )
        )
        listMyFriends.add(
            SelectFriendsModel(
                "",
                resources.getString(R.string.cordelia_john_firstName),
                R.drawable.user1,
                true
            )
        )

    }
}