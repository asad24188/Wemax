package com.wemax.mtech.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hbb20.CountryCodePicker
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivitySignUpBusinessBinding

class SignUpBusinessActivity : AppCompatActivity(), CountryCodePicker.OnCountryChangeListener {

    private var ccp: CountryCodePicker? = null
    private var countryCode: String? = null
    private var countryName: String? = null

    lateinit var binding: ActivitySignUpBusinessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBusinessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        onClick()

        ccp = findViewById(R.id.ccp)
        ccp!!.setOnCountryChangeListener(this)

        ccp!!.setDefaultCountryUsingNameCode("JP") //to set default country code as Japan
    }

    private fun onClick() {

        binding.arrowBack.setOnClickListener { finish() }
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, OTPVerificationActivity::class.java))
        }
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


        binding.edtFname.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.viewbarFname.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.viewbarFname.setBackgroundColor(resources.getColor(R.color.gray85))
                }
            }
        binding.edtEmail.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.viewbarEmail.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.viewbarEmail.setBackgroundColor(resources.getColor(R.color.gray85))
                }
            }
//        binding.ccpLayout.setOnClickListener {  }
        binding.edtPhone.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    binding.viewbarPhone.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.viewbarPhone.setBackgroundColor(resources.getColor(R.color.gray85))
                }
            }
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
    override fun onCountrySelected() {
        countryCode = ccp!!.selectedCountryCode
        countryName = ccp!!.selectedCountryName

//            Toast.makeText(this,"Country Code "+countryCode,Toast.LENGTH_SHORT).show()
//            Toast.makeText(this,"Country Name "+countryName,Toast.LENGTH_SHORT).show()
    }
}