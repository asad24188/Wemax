package com.wemax.mtech.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.remindrobort.app.utils.Utilities
import com.tabadol.tabadol.data.network.ApiClient
import com.wemax.mtech.Model.signup.SignUpResponseModel
import com.wemax.mtech.Model.verifydata.VerifyDataResponseModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityOtpverificationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OTPVerificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityOtpverificationBinding
    var otp = ""
    var name = ""
    var fullName = ""
    var countryCode = ""
    var phone = ""
    var email = ""
    var password = ""
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
            if (otpVerify.equals(""))
            {
                utilities.showFailureToast(this@OTPVerificationActivity,"Please Enter Otp Verify")
            }else if (otpVerify.equals(otp))
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
                        if (signupResponse!!.status ==  true) {
                            utilities.showSuccessToast(this@OTPVerificationActivity,signupResponse.message)
                            Handler(Looper.getMainLooper()).postDelayed({
                                val intent = Intent(this@OTPVerificationActivity,CompleteProfileActivity::class.java)
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