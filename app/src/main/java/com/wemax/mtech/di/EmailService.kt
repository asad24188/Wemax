package com.wemax.mtech.di

import android.util.Log
import javax.inject.Inject

class EmailService @Inject constructor(){

    fun sendEmail(email: String){
        Log.d("TAG","Email Sent")
    }
}