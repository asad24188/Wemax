package com.wemax.mtech.Fragment.calendar.activities

import com.wemax.mtech.Fragment.calendar.models.Event.Companion.eventsForDateAndTime
import androidx.appcompat.app.AppCompatActivity
import com.wemax.mtech.Fragment.calendar.adapter.CalendarAdapterNotInUse.OnItemListener
import androidx.annotation.RequiresApi
import android.os.Build
import android.os.Bundle
import com.wemax.mtech.Fragment.calendar.adapter.CalendarAdapterNotInUse
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Fragment.NotInUse.calendar.CalendarUtils
import com.wemax.mtech.Fragment.calendar.adapter.HourAdapter
import com.wemax.mtech.Fragment.calendar.models.HourEvent
import com.wemax.mtech.databinding.ActivityCalendarEventsBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*

class CalendarEventActivity : AppCompatActivity(), OnItemListener {
    private var binding: ActivityCalendarEventsBinding? = null

    // ref. link : https://www.youtube.com/watch?v=Aig99t-gNqM
    var dateAmd: LocalDate? = null
    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarEventsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        CalendarUtils.selectedDate = LocalDate.now()
        setWeekView()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun setWeekView() {
        binding!!.monthYearTV.text = CalendarUtils.monthYearFromDate(
            CalendarUtils.selectedDate)
        //        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        val df = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        val c = Calendar.getInstance().time
        val formattedDate = df.format(c)
        //        Toast.makeText(this, "Current Date: " + formattedDate, Toast.LENGTH_SHORT).show();
        val days = CalendarUtils.daysInWeekArray(
            CalendarUtils.selectedDate)
        val calendarAdapter = CalendarAdapterNotInUse(days, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(
            applicationContext, 7
        )
        binding!!.calendarRecyclerView.layoutManager = layoutManager
        binding!!.calendarRecyclerView.adapter = calendarAdapter
        binding!!.previousWeekAction.setOnClickListener { v: View? -> previousWeekAction(v) }
        binding!!.nextWeekAction.setOnClickListener { v: View? -> nextWeekAction(v) }
        binding!!.newEventAction.setOnClickListener { v: View? -> newEventAction(v) }
        /*        binding.dailyButton.setOnClickListener(v -> dailyAction(v));
        binding.nextDayAction.setOnClickListener(v -> nextDayAction(v));
        binding.previousDayAction.setOnClickListener(v -> previousDayAction(v));*/
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun previousWeekAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1)
        setWeekView()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun nextWeekAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1)
        setWeekView()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, date: LocalDate?) {
        CalendarUtils.selectedDate = date
        dateAmd = CalendarUtils.selectedDate
        setWeekView()
        setDayView()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        //        setEventAdpater();
        setDayView()
    }

    fun newEventAction(view: View?) {
        startActivity(Intent(this, AddReminderEventActivity::class.java))
    }

    // code for date
    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun setDayView() {
        val dayOfWeek =
            CalendarUtils.selectedDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        //        binding.dayOfWeekTV.setText(dayOfWeek);
        binding!!.monthDayTextAndDay.text =
            dayOfWeek + "," + " " + CalendarUtils.monthDayFromDate(
                CalendarUtils.selectedDate)
        setHourAdapter()
    }

    private fun setHourAdapter() {
        val hourAdapter = HourAdapter(applicationContext, hourEventList())
        binding!!.hourListView.adapter = hourAdapter
    }

    private fun hourEventList(): ArrayList<HourEvent?> {
        val list = ArrayList<HourEvent?>()
        for (hour in 0..23) {
            val time = LocalTime.of(hour, 0)
            val events = eventsForDateAndTime(CalendarUtils.selectedDate, time)
            val hourEvent = HourEvent(time, events)
            list.add(hourEvent)
        }
        return list
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun previousDayAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusDays(1)
        setDayView()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun nextDayAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusDays(1)
        setDayView()
    }
}