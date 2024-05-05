package com.example.gosocio.repository

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

    fun getPosts(): Flow<PagingData<Items>> {
        return Pager(
            config = PagingConfig(pageSize = 4, enablePlaceholders = false),
            remoteMediator = ItemRemoteMediator(appDatabase, apiService),
            pagingSourceFactory = { appDatabase.itemDao().getAllPosts() }
        ).flow
    }

    suspend fun getAllPosts(): ApiResponse {
        return apiService.getData("")
    }
}