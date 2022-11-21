package com.wemax.mtech.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.remindrobort.app.utils.Utilities
import com.tabadol.tabadol.data.network.ApiClient
import com.wemax.mtech.Model.verifydata.VerifyDataResponseModel
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private var countryCode: String? = null
    private lateinit var utilities: Utilities
    var fullName = ""
    var userName = ""
    private var email = ""
    var phone = ""
    var password = ""
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


    }

    private fun onClick() {

        binding.arrowBack.setOnClickListener { finish() }
        binding.btnSignup.setOnClickListener {
            userName = binding.edtUsername.text.toString().trim()
            fullName = binding.edtFname.text.toString().trim()
            email = binding.edtEmail.text.toString().trim()
            countryCode = binding.ccp.selectedCountryCodeWithPlus
            phone = binding.edtPhone.text.toString().trim()
            password = binding.edtPassword.text.toString().trim()
            val isValidEmail = utilities.isValidEmail(email)
            if (fullName == "")
            {
                utilities.showFailureToast(this@SignUpActivity,"Please Enter Full Name")
            }else if (userName == "")
            {
                utilities.showFailureToast(this@SignUpActivity,"Please Enter User Name")
            }else if (email == "")
            {
                utilities.showFailureToast(this@SignUpActivity,"Please Enter Email Address")
            }else if (!isValidEmail){
                utilities.showFailureToast(this@SignUpActivity,"Please Enter Valid Email Address")
            }else if (phone == "")
            {
                utilities.showFailureToast(this@SignUpActivity,"Please Enter Phone Number")
            }else if (password == "")
            {
                utilities.showFailureToast(this@SignUpActivity,"Please Enter Password")
            }else if (password.length<6)
            {
                utilities.showFailureToast(this@SignUpActivity,"Password Lenght Must Be Greater Than 6")
            }else{
                val number = countryCode+phone
                verifyData(email,number)
            }
        }
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
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
        binding.edtPhone.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.viewbarPhone.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.viewbarPhone.setBackgroundColor(resources.getColor(R.color.gray85))
                }
            }
        binding.edtPassword.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.viewbarPass.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.viewbarPass.setBackgroundColor(resources.getColor(R.color.gray85))
                }
            }
    }

    private fun verifyData(email: String, number: String) {
        val apiClient = ApiClient()
        if (utilities.isConnectingToInternet(this@SignUpActivity)) {
            binding.dotloader.visibility = View.VISIBLE
            apiClient.getApiService().verifyData(email,number)
                .enqueue(object : Callback<VerifyDataResponseModel> {
                    override fun onResponse(
                        call: Call<VerifyDataResponseModel>,
                        response: Response<VerifyDataResponseModel>
                    ) {
                        binding.dotloader.visibility = View.GONE
                        val signupResponse = response.body()
                        if (signupResponse!!.status) {
                            utilities.showSuccessToast(this@SignUpActivity,signupResponse.message)
                            val intent = Intent(this@SignUpActivity, OTPVerificationActivity::class.java)
                            intent.putExtra("name",userName)
                            intent.putExtra("fullname",fullName)
                            intent.putExtra("code",countryCode)
                            intent.putExtra("phone",phone)
                            intent.putExtra("email",email)
                            intent.putExtra("password",password)
                            intent.putExtra("otp",signupResponse.data.otp)
                            startActivity(intent)
                        } else {
                            utilities.showFailureToast(this@SignUpActivity, signupResponse.message)
                        }
                    }
                    override fun onFailure(call: Call<VerifyDataResponseModel>, t: Throwable) {
                        binding.dotloader.visibility = View.GONE
                    }
                })
        } else {
            utilities.showFailureToast(this@SignUpActivity, resources.getString(R.string.checkinternet))
        }
    }



}