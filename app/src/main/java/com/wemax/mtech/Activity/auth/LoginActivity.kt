package com.wemax.mtech.Activity.auth


import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityLoginBinding
import java.util.*


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var utilities: Utilities
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    var latitude = ""
    var longitude = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViews()
        onClick()

    }

    private fun initViews() {
        utilities = Utilities(this@LoginActivity)
        utilities.setWhiteBars(this@LoginActivity)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    private fun onClick() {

        binding.btnlogin.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.continueAsGuest.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.edtEmail.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {
                    binding.viewbarEmail.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.viewbarEmail.setBackgroundColor(resources.getColor(R.color.gray85))
                }
            }
        })
        binding.edtPassword.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {
                    binding.viewbarPass.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.viewbarPass.setBackgroundColor(resources.getColor(R.color.gray85))
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
        getLocation()
    }

    private fun getLocation() {
        if (isLocationEnabled()) {
            mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                val location: Location? = task.result
                if (location != null) {
                    val geocoder = Geocoder(this, Locale.getDefault())
                    val list: List<Address> = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    binding.apply {
                        latitude = list[0].latitude.toString()
                        longitude = list[0].longitude.toString()
                        if (!latitude.equals("")) {
                            utilities.saveString(this@LoginActivity, "latitude", latitude)
                            utilities.saveString(this@LoginActivity, "longitude", longitude)
                            Log.d("lattttt", latitude)
                            Log.d("longitude", longitude)
                        }
                    }
                }
            }
        } else {
            Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

}