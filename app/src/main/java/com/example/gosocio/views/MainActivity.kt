package com.example.gosocio.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gosocio.R
import com.example.gosocio.repository.ItemViewModel
import com.example.gosocio.repository.ItemViewModelFactory
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ItemViewModelFactory
    private val viewModel: ItemViewModel by viewModels { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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