package com.wemax.mtech.Fragment.NotInUse.calendar

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wemax.mtech.Fragment.calendar.activities.AddReminderEventActivity
import com.wemax.mtech.Fragment.calendar.adapter.CalendarAdapterNotInUse
import com.wemax.mtech.Fragment.calendar.adapter.HourAdapter
import com.wemax.mtech.Fragment.calendar.models.Event
import com.wemax.mtech.Fragment.calendar.models.HourEvent
import com.wemax.mtech.databinding.FragmentCalendarNotinUseBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*


class CalendarFragment_NotIn_Use : Fragment(), CalendarAdapterNotInUse.OnItemListener {

    lateinit var binding: FragmentCalendarNotinUseBinding

    // ref. link : https://www.youtube.com/watch?v=Aig99t-gNqM
    var dateAmd: LocalDate? = null

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalendarNotinUseBinding.inflate(inflater, container, false)

        CalendarUtils.selectedDate = LocalDate.now()
        setWeekView()
        return binding.root
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
            context, 7
        )
        binding!!.calendarRecyclerView.layoutManager = layoutManager
        binding!!.calendarRecyclerView.adapter = calendarAdapter
        binding!!.previousWeekAction.setOnClickListener { v: View? -> previousWeekAction(v) }
        binding!!.nextWeekAction.setOnClickListener { v: View? -> nextWeekAction(v) }
//        binding!!.newEventAction.setOnClickListener { v: View? -> newEventAction(v) }
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
        startActivity(Intent(context, AddReminderEventActivity::class.java))
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
        val hourAdapter = HourAdapter(context!!, hourEventList())
        binding!!.hourListView.adapter = hourAdapter
    }

    private fun hourEventList(): ArrayList<HourEvent?> {
        val list = ArrayList<HourEvent?>()
        for (hour in 0..23) {
            val time = LocalTime.of(hour, 0)
            val events = Event.eventsForDateAndTime(CalendarUtils.selectedDate, time)
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