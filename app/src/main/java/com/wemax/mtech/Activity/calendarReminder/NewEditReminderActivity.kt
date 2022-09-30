package com.wemax.mtech.Activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.Activity.auth.HomeActivity
import com.wemax.mtech.Adapter.NewReminderAdapter
import com.wemax.mtech.Model.calendarReminder.NewReminderModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityNewEditReminderBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NewEditReminderActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    lateinit var binding: ActivityNewEditReminderBinding
    val contextActivity = this@NewEditReminderActivity

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var SavedDay = 0
    var SavedMonth = 0
    var SavedYear = 0
    var SavedHour = 0
    var SavedMinute = 0
    var dateAndTime = ""

    var dateAndTimeAfterAllProcess = ""
    var listGroupMembers: ArrayList<NewReminderModel> = arrayListOf()
    lateinit var adapter: NewReminderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewEditReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        onClicks()
    }

    private fun onClicks() {

        binding.backpress.setOnClickListener { finish() }
        pickDate()
        binding.recyclerFriendshared.layoutManager = LinearLayoutManager(contextActivity)
        initRecyclerView()
        binding.recyclerFriendshared.adapter = NewReminderAdapter(contextActivity, listGroupMembers)

        binding.redLayout.setOnClickListener {
            binding.ivred.visibility = View.VISIBLE
            binding.ivyellow.visibility = View.GONE
            binding.ivgreen.visibility = View.GONE
        }
        binding.yellowLayout.setOnClickListener {
            binding.ivred.visibility = View.GONE
            binding.ivyellow.visibility = View.VISIBLE
            binding.ivgreen.visibility = View.GONE
        }
        binding.greenLayout.setOnClickListener {
            binding.ivred.visibility = View.GONE
            binding.ivyellow.visibility = View.GONE
            binding.ivgreen.visibility = View.VISIBLE
        }
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(contextActivity, HomeActivity::class.java))
        }
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

    private fun getDateTimeCalender() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
        cal.timeInMillis
    }

    private fun pickDate() {
        binding.dateTimeLayout.setOnClickListener {
            getDateTimeCalender()
            val cal = Calendar.getInstance()
            val datedialog = DatePickerDialog(this, this, year, month, day)
            datedialog.datePicker.minDate = cal.timeInMillis
//            datedialog.datePicker.minDate = System.currentTimeMillis() - 1000
            datedialog.show()

            // when you want to choose previous date too
/*            getDateTimeCalender()
            DatePickerDialog(this, this, year, month, day).show()*/
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        SavedDay = p3
        SavedMonth = p2 + 1
        SavedYear = p1

        getDateTimeCalender()
        TimePickerDialog(this, this, hour, minute, true).show()
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        SavedHour = p1
        SavedMinute = p2

        dateAndTime = "$SavedDay-$SavedMonth-$SavedYear $SavedHour:$SavedMinute"

        var dateFormatterTry = SimpleDateFormat("dd-MM-yyyy HH:mm").parse(dateAndTime)
        val timeFormatterTry = SimpleDateFormat("@ h:mm aa")
        val displayValueTry = timeFormatterTry.format(dateFormatterTry)
        binding.tvdateTime.text = "$SavedDay-$SavedMonth-$SavedYear " + displayValueTry
        // for 12 hours .
        dateAndTimeAfterAllProcess = binding.tvdateTime.text.toString()
//        dateFormat1(dateAndTime)  // dd-MM-yyyy, 10-May-2022"


    }
}