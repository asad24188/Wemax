package com.cheezycode.randomquote.api

import com.wemax.mtech.Model.homeApi.HomeResponse
import com.wemax.mtech.Model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("signup")
    suspend fun signup(
        @Field("signup_as") signup_as: String,
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("country_code") country_code: String,
        @Field("phone") phone: String,
        @Field("password") password: String): Response<LoginResponse>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("signin_as") signin_as: String,
        @Field("signin_with") signin_with: String,
        @Field("email") email: String,
        @Field("password") password: String) : Response<LoginResponse>

    @FormUrlEncoded
    @POST("user-home")
    suspend fun homeApi(
        @Field("user_id") user_id: String,
        @Field("latitude") latitude: String,
        @Field("longitude") longitude: String,
    ) : Response<HomeResponse>


}