package com.example.yelpapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BusinessAdapter(private val businesses: ArrayList<Businesses>) : RecyclerView.Adapter<BusinessAdapter.MyViewHolder>() {

    inner class MyViewHolder (itemView: View): RecyclerView.ViewHolder (itemView){
        // This class will represent a single row in our recyclerView list
        // This class also allows caching views and reuse them
        // Each MyViewHolder object keeps a reference to 3 view items in our row_item.xml file
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val address = itemView.findViewById<TextView>(R.id.tv_address)
        val foodType = itemView.findViewById<TextView>(R.id.tv_foodType)
        val reviews = itemView.findViewById<TextView>(R.id.tv_reviews)
        val distance = itemView.findViewById<TextView>(R.id.tv_distance)
        val price = itemView.findViewById<TextView>(R.id.tv_price)
        var ratingBarProgress = itemView.findViewById<RatingBar>(R.id.ratingBar).progress
        val businessImg = itemView.findViewById<ImageView>(R.id.profile_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate a layout from our XML (row_item.XML) and return the holder
        // create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val currentItem = businesses[position]
        holder.name.text = "${currentItem.name} "
        holder.address.text = currentItem.location.address1
        holder.foodType.text = currentItem.categories[position].title
        holder.reviews.text = currentItem.reviewCount.toString()
        holder.distance.text = currentItem.distance.toString()
        holder.price.text = currentItem.price
        holder.ratingBarProgress = currentItem.rating.toInt()


        // Get the context for glide
        val context = holder.itemView.context

        // Load the image from the url using Glide library
        Glide.with(context)
            .load(currentItem.image_url)
            .placeholder(R.drawable.ic_baseline_food_bank_24) // In case the image is not loaded show this placeholder image
            .into(holder.businessImg)

    }

    override fun getItemCount(): Int {
        // Return the size of your dataset (invoked by the layout manager)
        return businesses.size
    }
}