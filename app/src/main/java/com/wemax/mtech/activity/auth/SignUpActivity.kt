package com.wemax.mtech.activity.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cheezycode.randomquote.viewmodels.MainViewModel
import com.cheezycode.randomquote.viewmodels.MainViewModelFactory
import com.google.gson.Gson
import com.mtecsoft.swapme.view.activities.base.BaseActivity
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Model.login.User
import com.wemax.mtech.R
import com.wemax.mtech.activity.MainActivity
import com.wemax.mtech.databinding.ActivitySignUpBinding
import com.wemax.mtech.repository.Response
import com.wemax.mtech.utils.Constants
import com.wemax.mtech.utils.WemaxApplication

class SignUpActivity : BaseActivity() {

    private var countryCode: String? = null
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClick()

        mainViewModel.loginResponse.observe(this, Observer {
            when(it){
                is Response.Loading -> {
                    utilities.showProgressDialog(context,Constants.LOADING)
                }
                is Response.Success -> {
                    it.data?.let {
                        utilities.hideProgressDialog()
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        if (it.status == true){
                            saveUser(it.data)
                            gotoHome()
                        }
                    }
                }
                is Response.Error -> {
                    utilities.hideProgressDialog()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun saveUser(user: User) {
        val gson = Gson()
        val json = gson.toJson(user)
        utilities.saveString(context, Constants.USER, json)
        utilities.saveString(context,Constants.LOGGED_IN,Constants.TRUE)
    }

    private fun gotoHome() {

        startActivity(Intent(context, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private fun initViews() {

        context = this
        utilities = Utilities(this@SignUpActivity)
        utilities.setWhiteBars(this@SignUpActivity)
        val repository = (application as WemaxApplication).authRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)


    }

    private fun onClick() {

        binding.arrowBack.setOnClickListener { finish() }

        binding.btnSignup.setOnClickListener {

            var userName = binding.edtUsername.text.toString().trim()
            var fullName = binding.edtFname.text.toString().trim()
            var email = binding.edtEmail.text.toString().trim()
            countryCode = binding.ccp.selectedCountryCodeWithPlus
            var phone = binding.edtPhone.text.toString().trim()
            var password = binding.edtPassword.text.toString().trim()
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

                mainViewModel.signup(fullName,userName,email,countryCode.toString(),phone,password)
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

    private fun verifyData(
        fullName: String,
        userName: String,
        email: String,
        phone: String,
        password: String
    ) {




//
//        val apiClient = ApiClient()
//        if (utilities.isConnectingToInternet(this@SignUpActivity)) {
//            binding.dotloader.visibility = View.VISIBLE
//            apiClient.getApiService().verifyData(email,number)
//                .enqueue(object : Callback<VerifyDataResponseModel> {
//                    override fun onResponse(
//                        call: Call<VerifyDataResponseModel>,
//                        response: Response<VerifyDataResponseModel>
//                    ) {
//                        binding.dotloader.visibility = View.GONE
//                        val signupResponse = response.body()
//                        if (signupResponse!!.status) {
//                            utilities.showSuccessToast(this@SignUpActivity,signupResponse.message)
//                            val intent = Intent(this@SignUpActivity, OTPVerificationActivity::class.java)
//                            intent.putExtra("name",userName)
//                            intent.putExtra("fullname",fullName)
//                            intent.putExtra("code",countryCode)
//                            intent.putExtra("phone",phone)
//                            intent.putExtra("email",email)
//                            intent.putExtra("password",password)
//                            intent.putExtra("otp",signupResponse.data.otp)
//                            startActivity(intent)
//                        } else {
//                            utilities.showFailureToast(this@SignUpActivity, signupResponse.message)
//                        }
//                    }
//                    override fun onFailure(call: Call<VerifyDataResponseModel>, t: Throwable) {
//                        binding.dotloader.visibility = View.GONE
//                    }
//                })
//        } else {
//            utilities.showFailureToast(this@SignUpActivity, resources.getString(R.string.checkinternet))
//        }
    }



}