package com.wemax.mtech.Activity.notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wemax.mtech.Activity.home.event.EventDetailsActivity
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        binding.arrowBack.setOnClickListener {
            finish()
        }
        binding.showmyprofile.setOnClickListener {
            startActivity(Intent(this, ShowMyProfileActivity::class.java))

        }
        binding.myapointment.setOnClickListener {
            startActivity(Intent(this, MyAppointmentsActivity::class.java))

        }
    }
}