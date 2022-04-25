package com.example.yelpapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query



interface YelpFusion {

    //https://api.yelp.com/v3/businesses/search
    @GET("businesses/search")
    fun searchRestaurants(
        @Header("Authorization") authHeader: String,
        @Query("term") searchTerm: String,
        @Query("location") searchLocation: String) : Call<BusinessData>
}