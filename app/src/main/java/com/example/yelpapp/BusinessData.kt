package com.example.yelpapp

import com.google.gson.annotations.SerializedName

data class BusinessData(
    val businesses: List<Businesses>
)

data class Businesses(
    val rating: Float,
    val price: String,
    val categories: Categories,
    val reviewCount: Int,
    val name: String,
    val location: Locations,
    val distance: Float,

    // If you want to give a different variable name (different than what is in the received data),
    // you need to use @SerializedName, otherwise you can simply give the same variable name
    @SerializedName("picture") val imageUrl: Picture
)

data class Locations (
    val address: String
)

data class Categories (
    val title: String
)

data class Picture(
    val medium: String
)