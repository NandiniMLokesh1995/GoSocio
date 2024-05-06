package com.example.gosocio.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gosocio.entities.ApiResponse
import com.example.gosocio.entities.Items
import com.example.gosocio.network.ApiService
import com.example.gosocio.network.AppDatabase
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class ItemRepository(private val appDatabase: AppDatabase, private val apiService: ApiService) {

    suspend fun getAllPosts(): ApiResponse {
        return apiService.getData("")
    }
    suspend fun getAllPosts(next : String): ApiResponse {
        return apiService.getData(next)
    }
    suspend fun getAllPostsRoom(): LiveData<List<Items>> {
        return appDatabase.itemDao().getAllPosts()
    }

    suspend fun insertAllItemsRoom(items : List<Items>) {
        return appDatabase.itemDao().insertAll(items)
    }
}