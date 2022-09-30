package com.wemax.mtech

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wemax.mtech.Activity.auth.WellcomeSliderActivity
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 2000

    //    private String webURL = "https://quickstartapps.com/";
    private var schedulerService: ScheduledExecutorService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)


        Handler().postDelayed({
            val intent = Intent(this, WellcomeSliderActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT.toLong())

//        schedulerService_Method()

    }


    fun schedulerService_Method() {
        schedulerService = Executors.newSingleThreadScheduledExecutor()
        schedulerService!!.scheduleAtFixedRate({
            runOnUiThread {
                Toast.makeText(this@SplashActivity, "Show a message after every x seconds", Toast.LENGTH_SHORT).show()
            }
        }, 10, 10, TimeUnit.SECONDS) // after every 5 seconds, ya shaid 5 seconds
    }
}