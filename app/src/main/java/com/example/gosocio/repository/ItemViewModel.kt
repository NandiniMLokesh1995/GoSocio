package com.example.gosocio.repository

import android.util.Log
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

    suspend fun getData(){
        viewModelScope.launch {
            try {
                var data = repository.getAllPosts()
                 items.value = data.data // Assuming this returns List<Items>
                insertData(data.data)
                while(data.next!="") {
                    data= repository.getAllPosts(data.next)
                    items.value = data.data
                    insertData(data.data)
                }
            } catch (e: Exception) {
                // Handle insertion error, possibly logging or notifying the user
            }
        }
    }

    suspend fun insertData(items: List<Items>){
        viewModelScope.launch {
            try {
                 repository.insertAllItemsRoom(items)// Assuming this returns List<Items>

            } catch (e: Exception) {
                // Handle insertion error, possibly logging or notifying the user
            }
        }
    }

     fun getRoomData(){
        viewModelScope.launch {
            try {
                //repository.getAllPosts()
                val data =repository.getAllPostsRoom() // Assuming this returns List<Items>
                if(data.value.isNullOrEmpty()){
                    getData()
                }else{
                    items.value = data.value
                }

            } catch (e: Exception) {
                Log.e("Exception", e.message.toString())
                // Handle insertion error, possibly logging or notifying the user
            }
        }
    }
}