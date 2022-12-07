package com.wemax.mtech.Model.homeApi

data class PeopleAlsoViewed(
    val average_rating: String,
    val distance: Int,
    val id: Int,
    val latitude: String,
    val longitude: String,
    val name: String,
    val profile_image: String,
    val type: String
)