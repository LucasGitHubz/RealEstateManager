package com.example.realestatemanager.ui.property

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realestatemanager.R
import com.example.realestatemanager.model.Property
import com.squareup.picasso.Picasso

class PropertyRecyclerViewAdapter(private var properties: List<Property>, private var context: Context) :
    RecyclerView.Adapter<PropertyRecyclerViewAdapter.PropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_property_item, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = properties[position]

        if (!property.images.isNullOrEmpty() && !property.images?.get(0).isNullOrBlank()) {
            Picasso.get().load(property.images!![0]).into(holder.propertyIV)
        }
        holder.priceTV.text = "${property.price} $"
        holder.addressTV.text = property.address
        holder.numberOfBedsTV.text = property.beds.toString()
        holder.numberOfRoomsTV.text = property.rooms.toString()
        holder.numberOfBathroomsTV.text = property.bathrooms.toString()
        holder.surfaceTV.text = "${property.surface} m2"
    }

    override fun getItemCount(): Int {
        return properties.size
    }

    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var propertyIV: ImageView = itemView.findViewById(R.id.property_iv)
        var priceTV: TextView = itemView.findViewById(R.id.price_tv)
        var addressTV: TextView = itemView.findViewById(R.id.address_tv)
        var numberOfBedsTV: TextView = itemView.findViewById(R.id.number_of_bed_tv)
        var numberOfRoomsTV: TextView = itemView.findViewById(R.id.number_of_room_tv)
        var numberOfBathroomsTV: TextView = itemView.findViewById(R.id.number_of_bathroom_tv)
        var surfaceTV: TextView = itemView.findViewById(R.id.surface_tv)
    }
}