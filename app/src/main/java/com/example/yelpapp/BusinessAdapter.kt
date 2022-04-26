package com.example.yelpapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.DecimalFormat

class BusinessAdapter(private val businesses: ArrayList<Businesses>) : RecyclerView.Adapter<BusinessAdapter.MyViewHolder>() {

    private val TAG = "MAN WHAT"
    private val mileFormat = DecimalFormat("#.##")

    private fun convertToMiles(value: Float): String {
        val valToConvert = (value * 0.00062137)
        val miles = (mileFormat.format(valToConvert)).toDouble()
        return "$miles mi"
    }


    inner class MyViewHolder (itemView: View): RecyclerView.ViewHolder (itemView){
        val name = itemView.findViewById<TextView>(R.id.textViewName)
        val address = itemView.findViewById<TextView>(R.id.textViewAddress)
        val foodType = itemView.findViewById<TextView>(R.id.textViewCategorie)
        val reviews = itemView.findViewById<TextView>(R.id.textViewReviewCount)
        val distance = itemView.findViewById<TextView>(R.id.textViewDistance)
        val price = itemView.findViewById<TextView>(R.id.textViewPrice)
        var ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
        val businessImg = itemView.findViewById<ImageView>(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = businesses[position]
        holder.name.text = currentItem.name
        holder.address.text = currentItem.location.address1
        holder.foodType.text = currentItem.categories[0].title
        holder.reviews.text = "${currentItem.review_count} Reviews"
        holder.distance.text = convertToMiles(currentItem.distance)
        holder.price.text = currentItem.price
        holder.ratingBar.progress = (currentItem.rating*2).toInt()

        val context = holder.itemView.context

        Glide.with(context)
            .load(currentItem.image_url)
            .placeholder(R.drawable.ic_baseline_food_bank_24)
            .into(holder.businessImg)

    }

    override fun getItemCount(): Int {
        return businesses.size
    }
}