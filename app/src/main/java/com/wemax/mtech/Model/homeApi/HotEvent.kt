package com.wemax.mtech.Model.homeApi

data class HotEvent(
    val distance: Int,
    val event_image: String,
    val event_name: String,
    val id: Int,
    val latitude: String,
    val longitude: String
)