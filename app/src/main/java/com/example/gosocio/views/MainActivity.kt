package com.example.gosocio.views

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gosocio.R
import com.example.gosocio.network.AppDatabase
import com.example.gosocio.repository.ItemRepository
import com.example.gosocio.repository.ItemViewModel
import com.example.gosocio.repository.ItemViewModelFactory
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.gosocio.network.ApiClient
import com.google.android.material.divider.MaterialDividerItemDecoration


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val viewModelFactory = ItemViewModelFactory(ItemRepository(AppDatabase.getDatabase(this) ,ApiClient.apiService))

        val viewModel = ViewModelProvider(this, viewModelFactory).get(ItemViewModel::class.java)

        val itemDecorator = MaterialDividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
            dividerInsetEnd =60
            dividerInsetStart = 10
        }
        val recyclerview : RecyclerView = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.addItemDecoration(itemDecorator)
        val adapter = RecyclerItemsAdapter()
        recyclerview.adapter = adapter

        viewModel.posts.observe(this) { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        }
    }
}