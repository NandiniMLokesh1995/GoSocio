package com.example.gosocio.views

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gosocio.R
import com.example.gosocio.network.AppDatabase
import com.example.gosocio.repository.ItemRepository
import com.example.gosocio.repository.ItemViewModel
import com.example.gosocio.repository.ItemViewModelFactory
import androidx.lifecycle.*
import com.example.gosocio.network.ApiClient


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelFactory =ItemViewModelFactory(ItemRepository(AppDatabase.getDatabase(this),ApiClient.apiService))
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ItemViewModel::class.java)

        viewModel.getRoomData()
        val recyclerview : RecyclerView = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        var adapter :RecyclerItemsAdapter
        viewModel.items.observe(this, Observer { items ->
            Log.d("items", items.size.toString())
             adapter = RecyclerItemsAdapter(items)
            recyclerview.adapter = adapter
        })

    }
}