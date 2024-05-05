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
    val items : MutableLiveData<List<Items>> =MutableLiveData()
    val posts: LiveData<PagingData<Items>> by lazy {
        repository.getPosts().cachedIn(viewModelScope).asLiveData()
    }


    suspend fun getData(){
        viewModelScope.launch {
            try {
                 items.value = repository.getAllPosts().data // Assuming this returns List<Items>

            } catch (e: Exception) {
                // Handle insertion error, possibly logging or notifying the user
            }
        }
    }
}