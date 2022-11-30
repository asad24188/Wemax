package com.wemax.mtech.Model.login

data class LoginResponse(
    val status: Boolean,
    val message: String,
    val data: User
)