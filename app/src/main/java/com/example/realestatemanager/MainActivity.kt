package com.example.realestatemanager

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.realestatemanager.databinding.ActivityMainBinding
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.ui.favorite.FavoriteViewFragment
import com.example.realestatemanager.ui.map.MapViewFragment
import com.example.realestatemanager.ui.property.PropertyViewFragment
import com.example.realestatemanager.ui.property.PropertyViewModel
import com.example.realestatemanager.ui.property.PropertyViewState
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.collect
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private val fragments: HashMap<Int, Fragment> = object : HashMap<Int, Fragment>() {
        init {
            put(R.id.propertyViewFragment, PropertyViewFragment())
            put(R.id.mapViewFragment, MapViewFragment())
            put(R.id.favoriteViewFragment, FavoriteViewFragment())
        }
    }
    private var fragment = fragments[R.id.propertyViewFragment]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.container_view_fl, it).commit()
        }

        bottomNavigationView = binding.bottomNav
        setBottomBar()
    }

    private fun setBottomBar() {
        bottomNavigationView.setOnItemSelectedListener {
            fragment = fragments[it.itemId]
            fragment?.let { fragment ->
                supportFragmentManager.beginTransaction().replace(R.id.container_view_fl, fragment).commit()
            }
            return@setOnItemSelectedListener true
        }
    }
}