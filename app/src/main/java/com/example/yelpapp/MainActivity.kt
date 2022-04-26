package com.example.yelpapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://api.yelp.com/v3/"
    private val TAG = "MAN WHAT"
    private val API_KEY = "qscpX8e0-B0heUd8FKGP6yQmtj27fZEUYtsSKDptDQT96SRFvp2vk6_ZZZ_YrUtFc1OaRKen0yLmQukUPqJmgjojY3_-1LoJKANTcksalc-hkIwTcJ-XaWeQ4PRlYnYx"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun showSearchAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Search term missing")
        builder.setMessage("search term cannot be empty. Please enter a search term")
        builder.setIcon(android.R.drawable.ic_delete)
        builder.setNegativeButton("Okay"){ dialog, which ->
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showLocationAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Location missing")
        builder.setMessage("Location cannot be empty. Please enter a location")
        builder.setIcon(android.R.drawable.ic_delete)
        builder.setNegativeButton("Okay"){ dialog, which ->
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun onSearchClick(view: View){
        editTextSearch.hideKeyboard()
        editTextLocation.hideKeyboard()
        val searchTerm = editTextSearch.text.toString()
        val searchLocation = editTextLocation.text.toString()

        when {
            searchTerm.isEmpty() -> {
                showSearchAlert()
                return
            }
            searchLocation.isEmpty() -> {
                showLocationAlert()
            }
        }

            val restaurantList = ArrayList<Businesses>()

            val adapter = BusinessAdapter(restaurantList)

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

            recyclerView.adapter = adapter

            recyclerView.layoutManager = LinearLayoutManager(this)

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val yelpAPI = retrofit.create(YelpFusion::class.java)

            yelpAPI.searchRestaurants("Bearer $API_KEY", searchTerm, searchLocation).enqueue(object : Callback<BusinessData>{

                override fun onFailure(call: Call<BusinessData>, t: Throwable) {
                    Log.d(TAG, "onFailure : $t")
                }

                override fun onResponse(call: Call<BusinessData>, response: Response<BusinessData>) {
                    Log.d(TAG, "onResponse: $response")

                    val body = response.body()

                    if (body == null){
                        Log.w(TAG, "Valid response was not received")
                        return
                    }
                    restaurantList.addAll(body.businesses)
                    adapter.notifyDataSetChanged()
                }
            })
    }
}
