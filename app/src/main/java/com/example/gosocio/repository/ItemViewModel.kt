package com.example.gosocio.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gosocio.entities.Items
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {
    val posts: LiveData<PagingData<Items>> by lazy {
        repository.getPosts().cachedIn(viewModelScope).asLiveData()
    }

}