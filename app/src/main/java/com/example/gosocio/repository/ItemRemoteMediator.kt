package com.example.gosocio.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.gosocio.entities.Items
import com.example.gosocio.network.ApiService
import com.example.gosocio.network.AppDatabase

@OptIn(ExperimentalPagingApi::class)
class ItemRemoteMediator(
    private val appDatabase: AppDatabase,
    private val service: ApiService
) : RemoteMediator<Int, Items>() {

    private var nextKey: String? = null

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Items>
    ): MediatorResult {

        Log.d("ItemRemoteMediator", "LoadType: $loadType, nextKey: $nextKey")
        return try {
            val nextPage = when (loadType) {
                LoadType.REFRESH -> ""
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> nextKey
            }

            val response = service.getData(nextPage)
            val data = response.data

            if (loadType == LoadType.REFRESH) {
                appDatabase.itemDao().clearAll()
            }
            appDatabase.itemDao().insertAll(data)
            nextKey = response.next

            MediatorResult.Success(endOfPaginationReached = response.next.isNullOrEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
