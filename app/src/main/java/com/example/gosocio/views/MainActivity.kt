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
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val viewModelFactory =ItemViewModelFactory(ItemRepository(AppDatabase.getDatabase(this) ,ApiClient.apiService))

        val viewModel = ViewModelProvider(this, viewModelFactory).get(ItemViewModel::class.java)


        val recyclerview : RecyclerView = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerItemsAdapter()
        recyclerview.adapter = adapter

        viewModel.items.observe(this, Observer { items ->
            Log.d("items", items.size.toString())
        })
        viewModel.posts.observe(this) { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        }
    }
}