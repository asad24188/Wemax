package com.wemax.mtech.Activity.notification

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wemax.mtech.Adapter.AdapterMyApoints
import com.wemax.mtech.Adapter.AdapterUpcomingsApoints
import com.wemax.mtech.Model.notification.DataModelMyAppoints
import com.wemax.mtech.Model.notification.DataModelUpcomingsAppoints
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityMyAppointmentsBinding
import java.util.ArrayList

class MyAppointmentsActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyAppointmentsBinding
    lateinit var newArrayList: ArrayList<DataModelMyAppoints>
    lateinit var newArrayList2: ArrayList<DataModelUpcomingsAppoints>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAppointmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        binding.arrowBack.setOnClickListener {
            finish()
        }
        binding.recentrcv.layoutManager =
            LinearLayoutManager(this)
        binding.recentrcv.setHasFixedSize(true)
        newArrayList = arrayListOf<DataModelMyAppoints>()
        getData()
        binding.recentrcv.adapter = AdapterMyApoints(this, newArrayList)

        binding.uprcv.layoutManager =
            LinearLayoutManager(this)
        binding.uprcv.setHasFixedSize(true)
        newArrayList2 = arrayListOf<DataModelUpcomingsAppoints>()
        getData2()
        binding.uprcv.adapter = AdapterUpcomingsApoints(this, newArrayList2,layoutInflater)


        binding.recentapointment.setOnClickListener {
            binding.recentapointment.setBackgroundResource(R.drawable.back_appoint2)
            binding.recentText.setTextColor(Color.parseColor("#FFFFFF"))
            binding.upapointment.setBackgroundResource(R.drawable.back_appoint)
            binding.upText.setTextColor(Color.parseColor("#000000"))
            binding.recentrcv.visibility = View.VISIBLE
            binding.uprcv.visibility = View.GONE
        }
        binding.upapointment.setOnClickListener {
            binding.upapointment.setBackgroundResource(R.drawable.back_appoint2)
            binding.upText.setTextColor(Color.parseColor("#FFFFFF"))
            binding.recentapointment.setBackgroundResource(R.drawable.back_appoint)
            binding.recentText.setTextColor(Color.parseColor("#000000"))
            binding.recentrcv.visibility = View.GONE
            binding.uprcv.visibility = View.VISIBLE
        }
    }

    private fun getData() {
        newArrayList = arrayListOf()
        newArrayList.add(
            DataModelMyAppoints(
                R.drawable.provider1,
                "Bloomington Beauty Salon",
                "5 July 2022",
                "05:00 PM",
                "05:00 PM"
            )
        )
        newArrayList.add(
            DataModelMyAppoints(
                R.drawable.provider1,
                "Bloomington Beauty Salon",
                "5 July 2022",
                "05:00 PM",
                "05:00 PM"
            )
        )
        newArrayList.add(
            DataModelMyAppoints(
                R.drawable.provider1,
                "Bloomington Beauty Salon",
                "5 July 2022",
                "05:00 PM",
                "05:00 PM"
            )
        )
        newArrayList.add(
            DataModelMyAppoints(
                R.drawable.provider1,
                "Bloomington Beauty Salon",
                "5 July 2022",
                "05:00 PM",
                "05:00 PM"
            )
        )
    }
    private fun getData2() {
        newArrayList2 = arrayListOf()
        newArrayList2.add(
            DataModelUpcomingsAppoints(
                R.drawable.provider1,
                "Bloomington Beauty Salon",
                "5 July 2022",
                "05:00 PM",
                "05:00 PM"
            )
        )
            newArrayList2.add(
                DataModelUpcomingsAppoints(
                    R.drawable.provider1,
                    "Bloomington Beauty Salon",
                    "5 July 2022",
                    "05:00 PM",
                    "05:00 PM"
                )
        )
        newArrayList2.add(
            DataModelUpcomingsAppoints(
                R.drawable.provider1,
                "Bloomington Beauty Salon",
                "5 July 2022",
                "05:00 PM",
                "05:00 PM"
            ))
        newArrayList2.add(
            DataModelUpcomingsAppoints(
                R.drawable.provider1,
                "Bloomington Beauty Salon",
                "5 July 2022",
                "05:00 PM",
                "05:00 PM"
            ))

    }
}