package com.wemax.mtech.utils

import android.app.Application
import android.hardware.biometrics.BiometricPrompt
import com.cheezycode.randomquote.api.ApiService
import com.cheezycode.randomquote.api.RetrofitHelper
import com.cheezycode.randomquote.repository.AuthRepository


class WemaxApplication : Application() {

    lateinit var authRepository: AuthRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(ApiService::class.java)
        authRepository = AuthRepository(quoteService, applicationContext)
    }
}