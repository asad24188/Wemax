package com.wemax.mtech.Activity.calendarReminder

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.Adapter.StatusAdapter
import com.wemax.mtech.Adapter.groups.SelectFriendsAdapter
import com.wemax.mtech.Model.StatusModel
import com.wemax.mtech.Model.groups.AddedFriendsModel
import com.wemax.mtech.Model.groups.SelectFriendsModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityCalenderAccessBinding
import java.util.*
import kotlin.collections.ArrayList

class CalenderAccessActivity : AppCompatActivity() {
    var listMyFriends: ArrayList<SelectFriendsModel> = arrayListOf()
    lateinit var binding: ActivityCalenderAccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCalenderAccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
binding.backpress.setOnClickListener {
    finish()
}
        binding.myFriendsRecycler.layoutManager = LinearLayoutManager(this)
        initRecyclerView()
        binding.myFriendsRecycler.adapter = SelectFriendsAdapter(this, listMyFriends,layoutInflater)
    }
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