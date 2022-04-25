package com.example.yelpapp

import com.google.gson.annotations.SerializedName

data class BusinessData(
    val total: Int,
    val businesses: List<Businesses>
)

data class Businesses(
    val rating: Float,
    val price: String,
    val categories: List<Categories>,
    val reviewCount: Int,
    val name: String,
    val image_url: String,
    val location: Location,
    val distance: Float,

)

data class Location (
    val address1: String
)

data class Categories (
    val title: String
)
