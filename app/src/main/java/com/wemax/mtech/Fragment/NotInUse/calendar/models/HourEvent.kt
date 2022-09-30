package com.wemax.mtech.Fragment.calendar.models

import java.time.LocalTime
import java.util.ArrayList

data class HourEvent(var time: LocalTime, var events: ArrayList<Event>)