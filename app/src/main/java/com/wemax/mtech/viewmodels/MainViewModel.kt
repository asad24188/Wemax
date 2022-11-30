package com.cheezycode.randomquote.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheezycode.randomquote.repository.AuthRepository
import com.wemax.mtech.Model.login.LoginResponse
import com.wemax.mtech.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: AuthRepository) : ViewModel() {


    fun signup(name: String, userName: String,
               email: String,country_code:String,
               phone: String,password: String) {

        viewModelScope.launch(Dispatchers.IO){
            repository.signup(name,userName,email,country_code,phone,password)
        }
    }

    fun login(email: String,password: String) {

        viewModelScope.launch(Dispatchers.IO){
            repository.login(email,password)
        }
    }

    val loginResponse : LiveData<Response<LoginResponse>>
    get() = repository.loginResponse
}