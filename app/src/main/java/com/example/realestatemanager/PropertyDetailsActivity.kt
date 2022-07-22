package com.example.realestatemanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.realestatemanager.databinding.ActivityPropertyDetailsBinding
import com.example.realestatemanager.model.Property
import com.squareup.picasso.Picasso

class PropertyDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPropertyDetailsBinding

    private var property: Property = Property()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPropertyDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        property = intent.getParcelableExtra<Property>("property") ?: Property()

        setViews()
    }

    private fun setViews() {
        binding.backCv.setOnClickListener { onBackPressed() }

        if (!property.images.isNullOrEmpty() && !property.images?.get(0).isNullOrBlank()) {
            Picasso.get().load(property.images!![0]).into(binding.headerIv)
        }

        binding.detailsPriceTv.text = "${property.price} $"
        binding.detailsTypeTv.text = property.type
        binding.detailsBedsTv.text = "${property.beds}"
        binding.detailsBathroomTv.text = "${property.bathrooms}"
        binding.detailsSurfaceTv.text = "${property.surface} m2"

        binding.detailsAddressTv.text = property.address

        val dateOfUpdate = Utils().fullDateFormat.parse(property.dateOfUpdate ?: "")
        binding.dateOfUpdateTv.text = getString(R.string.lastUpdate, dateOfUpdate?.let { Utils().dateFormat.format(it) } ?: getString(R.string.unknown))
    }
}