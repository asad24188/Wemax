package com.wemax.mtech.Fragment.calendar.activities

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import android.os.Build
import android.os.Bundle
import android.view.View
import com.wemax.mtech.Fragment.NotInUse.calendar.CalendarUtils
import com.wemax.mtech.Fragment.calendar.models.Event
import com.wemax.mtech.R
import java.time.LocalDate
import java.time.LocalTime

class AddReminderEventActivity : AppCompatActivity() {
    private var eventNameET: EditText? = null
    private var eventDateTV: TextView? = null
    private var eventTimeTV: TextView? = null
    private var time: LocalTime? = null
    private val date: LocalDate? = null
    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder_event_add)
        initWidgets()
        time = LocalTime.now()
        //        date = LocalDate.now();
        eventDateTV!!.text =
            "Date: " + CalendarUtils.formattedDate(
                CalendarUtils.selectedDate)
        eventTimeTV!!.text =
            "Time: " + CalendarUtils.formattedTime(time)
    }

    private fun initWidgets() {
        eventNameET = findViewById(R.id.eventNameET)
        eventDateTV = findViewById(R.id.eventDateTV)
        eventTimeTV = findViewById(R.id.eventTimeTV)
    }

    fun saveEventAction(view: View?) {
        val eventName = eventNameET!!.text.toString()
        val newEvent = Event(eventName, CalendarUtils.selectedDate, time!!)
        Event.eventsList.add(newEvent)
        finish()
    }
}