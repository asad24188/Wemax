package com.cheezycode.randomquote.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cheezycode.randomquote.api.ApiService
import com.wemax.mtech.Model.homeApi.HomeResponse
import com.wemax.mtech.Model.login.LoginResponse
import com.wemax.mtech.repository.Response
import com.wemax.mtech.utils.Constants
import com.wemax.mtech.utils.NetworkUtils

class AuthRepository(private val apiService: ApiService, private val applicationContext: Context) {


    //Login SignUp
    private val loginLiveData = MutableLiveData<Response<LoginResponse>>()
    val loginResponse: LiveData<Response<LoginResponse>>
    get() = loginLiveData

    suspend fun signup(
        name: String,
        userName: String,
        email: String,
        country_code: String,
        phone: String,
        password: String
    ) {

        if(NetworkUtils.isInternetAvailable(applicationContext)){
            try {
                loginLiveData.postValue(Response.Loading())
                val result = apiService.signup("user",name,userName,email,country_code,phone,password)
                if(result?.body() != null){
                    loginLiveData.postValue(Response.Success(result.body()))
                }
            }catch (e: Exception){
                loginLiveData.postValue(Response.Error(e.message.toString()))
            }
        }
        else{ Toast.makeText(applicationContext, Constants.NO_INTERNET, Toast.LENGTH_SHORT).show() }

    }



    suspend fun login(email: String, password: String) {
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            try {
                loginLiveData.postValue(Response.Loading())
                val result = apiService.login("1","email",email,password)
                if(result?.body() != null){
                    loginLiveData.postValue(Response.Success(result.body()))
                }
            }catch (e: Exception){
                loginLiveData.postValue(Response.Error(e.message.toString()))
            }
        }
        else{ Toast.makeText(applicationContext, Constants.NO_INTERNET, Toast.LENGTH_SHORT).show() }
    }



    //Home Apis
    private val homeLiveData = MutableLiveData<Response<HomeResponse>>()
    val homeResponse: LiveData<Response<HomeResponse>>
        get() = homeLiveData


    suspend fun homoApi(userId: String, latitude: String, longitude: String) {
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            try {
                homeLiveData.postValue(Response.Loading())
                val result = apiService.homeApi(userId,latitude,longitude)
                if(result?.body() != null){
                    homeLiveData.postValue(Response.Success(result.body()))
                }
            }catch (e: Exception){
                homeLiveData.postValue(Response.Error(e.message.toString()))
            }
        }
        else{ Toast.makeText(applicationContext, Constants.NO_INTERNET, Toast.LENGTH_SHORT).show() }
    }

}







