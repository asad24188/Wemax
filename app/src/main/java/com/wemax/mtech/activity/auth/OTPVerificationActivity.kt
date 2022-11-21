package com.wemax.mtech.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.google.gson.Gson
import com.remindrobort.app.utils.Utilities
import com.tabadol.tabadol.data.network.ApiClient
import com.wemax.mtech.Model.signup.SignUpResponseModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityOtpverificationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OTPVerificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityOtpverificationBinding
    private var otp = ""
    var name = ""
    private var fullName = ""
    private var countryCode = ""
    var phone = ""
    private var email = ""
    private var password = ""
    private lateinit var utilities: Utilities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        click()
    }

    private fun initViews() {
        utilities = Utilities(this@OTPVerificationActivity)
        val intent = intent
        otp = intent.getStringExtra("otp").toString()
        name =intent.getStringExtra("name").toString()
        fullName = intent.getStringExtra("fullname").toString()
        countryCode = intent.getStringExtra("code").toString()
        phone = intent.getStringExtra("phone").toString()
        email = intent.getStringExtra("email").toString()
        password = intent.getStringExtra("password").toString()


    }

    private fun click() {
        binding.arrowBack.setOnClickListener { finish() }
        binding.btnVerify.setOnClickListener {
            val otpVerify = binding.edOtp.text.trim()
            if (otpVerify == "")
            {
                utilities.showFailureToast(this@OTPVerificationActivity,"Please Enter Otp Verify")
            }else if (otpVerify == otp)
            {
                utilities.showFailureToast(this@OTPVerificationActivity,"Otp Doesn't Match")
            }else{
                signUp()
            }
        }
    }

    private fun signUp() {
        val apiClient = ApiClient()
        if (utilities.isConnectingToInternet(this@OTPVerificationActivity)) {
            binding.dotloader.visibility = View.VISIBLE
            apiClient.getApiService().signUp("user",name,fullName,email,countryCode,phone,password)
                .enqueue(object : Callback<SignUpResponseModel> {
                    override fun onResponse(
                        call: Call<SignUpResponseModel>,
                        response: Response<SignUpResponseModel>
                    ) {
                        binding.dotloader.visibility = View.GONE
                        val signupResponse = response.body()
                        if (signupResponse!!.status) {
                            utilities.showSuccessToast(this@OTPVerificationActivity,signupResponse.message)
                            val gson = Gson()
                            val json = gson.toJson(signupResponse.data)
                            utilities.saveString(this@OTPVerificationActivity, "loginResponse", json)
                            utilities.saveString(this@OTPVerificationActivity, "loggedin", "loggedin")
                            utilities.saveString(this@OTPVerificationActivity, "loginAs", "apilogin")
                            Handler(Looper.getMainLooper()).postDelayed({
                                val intent = Intent(this@OTPVerificationActivity,
                                    CompleteProfileActivity::class.java)
                                startActivity(intent)
                            },1000)
                        } else {
                            utilities.showFailureToast(this@OTPVerificationActivity, signupResponse.message)
                        }
                    }
                    override fun onFailure(call: Call<SignUpResponseModel>, t: Throwable) {
                        binding.dotloader.visibility = View.GONE
                    }
                })
        } else {
            utilities.showFailureToast(this@OTPVerificationActivity, resources.getString(R.string.checkinternet))
        }
    }

}