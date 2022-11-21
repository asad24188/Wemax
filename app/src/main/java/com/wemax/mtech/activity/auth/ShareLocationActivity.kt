package com.wemax.mtech.activity.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.databinding.ActivityShareLocationBinding
import com.wemax.mtech.utils.Constants

class ShareLocationActivity : AppCompatActivity() {
    lateinit var binding: ActivityShareLocationBinding
    lateinit var utilities: Utilities
    private var lattitude = ""
    private var longitude = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClicks()
    }

    private fun initViews() {

        utilities = Utilities(this@ShareLocationActivity)
        utilities.setWhiteBars(this@ShareLocationActivity)


        lattitude = utilities.getString(this@ShareLocationActivity, "latitude")
        longitude = utilities.getString(this@ShareLocationActivity, "longitude")
    }

    private fun onClicks() {
        binding.arrowBack.setOnClickListener { finish() }
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this@ShareLocationActivity, HobbiesInterestActivity::class.java)
            Constants.lattitude = lattitude
            Constants.longitude = longitude
            startActivity(intent)
        }

        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, HobbiesInterestActivity::class.java))
        }
    }
}