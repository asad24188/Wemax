package com.wemax.mtech.activity.home.serviceDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Adapter.calendarReminder.calendar.CalendarAdapter
import com.wemax.mtech.Adapter.home.serviceDetails.SelectDateAndTimeAdapter
import com.wemax.mtech.Model.calendarReminder.CalendarReminderModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivitySelectDateTimeBinding
import java.text.SimpleDateFormat
import java.util.*

class SelectDateTimeActivity : AppCompatActivity() {
    lateinit var binding: ActivitySelectDateTimeBinding
    val contextActivity = this@SelectDateTimeActivity
    lateinit var utils: Utilities

    private val lastDayInCalendar = Calendar.getInstance(Locale.ENGLISH)
    private val sdf = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val cal = Calendar.getInstance(Locale.ENGLISH)

    // current date
    private val currentDate = Calendar.getInstance(Locale.ENGLISH)
    private val currentDay = currentDate[Calendar.DAY_OF_MONTH]
    private val currentMonth = currentDate[Calendar.MONTH]
    private val currentYear = currentDate[Calendar.YEAR]

    // selected date
    private var selectedDay: Int = currentDay
    private var selectedMonth: Int = currentMonth
    private var selectedYear: Int = currentYear

    // all days in month
    var listReminders = arrayListOf<CalendarReminderModel>()
    lateinit var adapterReminders: SelectDateAndTimeAdapter
    private val dates = ArrayList<Date>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectDateTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        onClicks()
    }

    private fun initViews() {

        utils = Utilities(contextActivity)


        binding.arrowBack.setOnClickListener {
            finish()
        }
        initReminders()
        binding.reminderRecyclerView.layoutManager = LinearLayoutManager(contextActivity)
        adapterReminders = SelectDateAndTimeAdapter(contextActivity!!, listReminders)
        binding.reminderRecyclerView.adapter = adapterReminders

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.calendarRecyclerView)
        lastDayInCalendar.add(Calendar.MONTH, 6)
        setUpCalendar()
    }


    private fun initReminders() {
        listReminders.add(CalendarReminderModel("", "12 AM", "", ""))
        listReminders.add(CalendarReminderModel("", "01 AM", "", ""))
        listReminders.add(CalendarReminderModel("", "02 AM", "", ""))
        listReminders.add(
            CalendarReminderModel(
                "",
                "03 AM",
                resources.getString(R.string.new_year_party_event),
                resources.getString(R.string.time_)
            )
        )
        listReminders.add(CalendarReminderModel("", "04 AM", "", ""))
        listReminders.add(CalendarReminderModel("", "05 AM", "", ""))
        listReminders.add(CalendarReminderModel("", "06 AM", "", ""))
        listReminders.add(CalendarReminderModel("", "07 AM", "", ""))
        listReminders.add(CalendarReminderModel("", "08 AM", "", ""))
        listReminders.add(CalendarReminderModel("", "09 AM", "", ""))
        listReminders.add(CalendarReminderModel("", "10 AM", "", ""))
        listReminders.add(CalendarReminderModel("", "11 AM", "", ""))
        listReminders.add(CalendarReminderModel("", "12 PM", "", ""))
        listReminders.add(CalendarReminderModel("", "01 PM", "", ""))
        listReminders.add(CalendarReminderModel("", "02 PM", "", ""))
        listReminders.add(CalendarReminderModel("", "03 PM", "", ""))
        listReminders.add(CalendarReminderModel("", "04 PM", "", ""))
        listReminders.add(CalendarReminderModel("", "05 PM", "", ""))
        listReminders.add(CalendarReminderModel("", "06 PM", "", ""))
        listReminders.add(CalendarReminderModel("", "07 PM", "", ""))
        listReminders.add(CalendarReminderModel("", "08 PM", "", ""))
        listReminders.add(CalendarReminderModel("", "09 PM", "", ""))
        listReminders.add(CalendarReminderModel("", "10 PM", "", ""))
        listReminders.add(CalendarReminderModel("", "11 PM", "", ""))
    }

    private fun onClicks() {
        binding.previousWeekAction!!.setOnClickListener {
            if (cal.after(currentDate)) {
                cal.add(Calendar.MONTH, -1)
                if (cal == currentDate)
                    setUpCalendar()
                else
                    setUpCalendar(changeMonth = cal)
            }
        }


        binding.nextWeekAction!!.setOnClickListener {
            if (cal.before(lastDayInCalendar)) {
                cal.add(Calendar.MONTH, 1)
                setUpCalendar(changeMonth = cal)
            }
        }
        binding.arrowBack.setOnClickListener {
            finish()
        }
    }

    private fun setUpCalendar(changeMonth: Calendar? = null) {
        binding.monthYearTV!!.text = sdf.format(cal.time)
        val monthCalendar = cal.clone() as Calendar
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        selectedDay =
            when {
                changeMonth != null -> changeMonth.getActualMinimum(Calendar.DAY_OF_MONTH)
                else -> currentDay
            }
        selectedMonth =
            when {
                changeMonth != null -> changeMonth[Calendar.MONTH]
                else -> currentMonth
            }
        selectedYear =
            when {
                changeMonth != null -> changeMonth[Calendar.YEAR]
                else -> currentYear
            }

        var currentPosition = 0
        dates.clear()
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)

        while (dates.size < maxDaysInMonth) {
            // get position of selected day
            if (monthCalendar[Calendar.DAY_OF_MONTH] == selectedDay)
                currentPosition = dates.size
            dates.add(monthCalendar.time)
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        // Assigning calendar view.
        val layoutManager =
            LinearLayoutManager(contextActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.calendarRecyclerView!!.layoutManager = layoutManager
        val calendarAdapter = CalendarAdapter(contextActivity, dates, currentDate, changeMonth)
        binding.calendarRecyclerView!!.adapter = calendarAdapter

        when {
            currentPosition > 2 -> binding.calendarRecyclerView!!.scrollToPosition(currentPosition - 3)
            maxDaysInMonth - currentPosition < 2 -> binding.calendarRecyclerView!!.scrollToPosition(
                currentPosition
            )
            else -> binding.calendarRecyclerView!!.scrollToPosition(currentPosition)
        }

        selectedMonth += 1
        calendarAdapter.setOnItemClickListener(object : CalendarAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val clickCalendar = Calendar.getInstance()
                clickCalendar.time = dates[position]
                selectedDay = clickCalendar[Calendar.DAY_OF_MONTH]
                var datee = "$selectedDay.$selectedMonth.$selectedYear"
                Toast.makeText(
                    contextActivity,
                    datee.toString(),
                    Toast.LENGTH_SHORT
                ).show()
/*
                var date = "22/10/2016 6:30:00 PM"
                var spf = SimpleDateFormat("dd/mm/yyyy hh:mm:ss aaa")
*/
/*                var date = "$selectedDay.$selectedMonth.$selectedYear"
                var spf = SimpleDateFormat("dd.mm.yyyy")
                val newDate = spf.parse(date)
                spf = SimpleDateFormat("MMM yyyy")
                date = spf.format(newDate)

                Toast.makeText(
                    this@MainActivity,
                    date.toString(),
                    Toast.LENGTH_SHORT
                ).show()*/
            }
        })
    }
}