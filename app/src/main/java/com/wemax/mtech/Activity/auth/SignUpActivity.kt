package com.wemax.mtech.Activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hbb20.CountryCodePicker
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Activity.OTPVerificationActivity
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), CountryCodePicker.OnCountryChangeListener {

    private var ccp: CountryCodePicker? = null
    private var countryCode: String? = null
    private var countryName: String? = null
    private lateinit var utilities: Utilities

    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViews()
        onClick()


    }

    private fun initViews() {
        utilities = Utilities(this@SignUpActivity)
        utilities.setWhiteBars(this@SignUpActivity)
        ccp = findViewById(R.id.ccp)
        ccp!!.setOnCountryChangeListener(this)
        ccp!!.setDefaultCountryUsingNameCode("pk") //to set default country code as Japan

    }

    private fun onClick() {

        binding.arrowBack.setOnClickListener { finish() }
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, TouchVerificationActivity::class.java))
        }
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


        binding.edtFname.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {
                    binding.viewbarFname.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.viewbarFname.setBackgroundColor(resources.getColor(R.color.gray85))
                }
            }
        })
        binding.edtEmail.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {
                    binding.viewbarEmail.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.viewbarEmail.setBackgroundColor(resources.getColor(R.color.gray85))
                }
            }
        })
//        binding.ccpLayout.setOnClickListener {  }
        binding.edtPhone.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {
                    binding.viewbarPhone.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.viewbarPhone.setBackgroundColor(resources.getColor(R.color.gray85))
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

    override fun onCountrySelected() {
        countryCode = ccp!!.selectedCountryCode
        countryName = ccp!!.selectedCountryName

//            Toast.makeText(this,"Country Code "+countryCode,Toast.LENGTH_SHORT).show()
//            Toast.makeText(this,"Country Name "+countryName,Toast.LENGTH_SHORT).show()
    }
}