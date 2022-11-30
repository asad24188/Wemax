package com.wemax.mtech.activity.auth


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
import androidx.lifecycle.ViewModelProvider
import com.cheezycode.randomquote.viewmodels.MainViewModel
import com.cheezycode.randomquote.viewmodels.MainViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.mtecsoft.swapme.view.activities.base.BaseActivity
import com.remindrobort.app.utils.Utilities
import com.wemax.mtech.Model.login.User
import com.wemax.mtech.R
import com.wemax.mtech.databinding.ActivityLoginBinding
import com.wemax.mtech.repository.Response
import com.wemax.mtech.utils.Constants
import com.wemax.mtech.utils.WemaxApplication
import java.util.*

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClick()

        mainViewModel.loginResponse.observe(this, androidx.lifecycle.Observer {
            when(it){
                is Response.Loading -> {
                    utilities.showProgressDialog(context, Constants.LOADING)
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

        startActivity(Intent(context,HomeActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private fun initViews() {

        context = this
        utilities = Utilities(this@LoginActivity)
        utilities.setWhiteBars(this@LoginActivity)
        val repository = (application as WemaxApplication).authRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

    }

    private fun onClick() {

        binding.btnlogin.setOnClickListener {
            var email = binding.edtEmail.text.toString()
            var pass = binding.edtPassword.text.toString()
            if (!email.isEmpty() && !pass.isEmpty()){
                mainViewModel.login(email,pass)
            }

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


}