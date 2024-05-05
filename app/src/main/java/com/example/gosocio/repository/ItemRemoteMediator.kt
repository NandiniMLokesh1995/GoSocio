package com.example.gosocio.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.gosocio.entities.ItemsDao
import com.example.gosocio.entities.Items
import com.example.gosocio.network.ApiService
import com.example.gosocio.network.AppDatabase

@OptIn(ExperimentalPagingApi::class)
class ItemRemoteMediator(
    private val appDatabase: AppDatabase,
    private val service: ApiService
) : RemoteMediator<Int, Items>() {

    private val itemsDao: ItemsDao= appDatabase.itemDao()
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Items>): MediatorResult {
        try {
            itemsDao.getAllPosts()
            val initial_response = service.getData("")

            val pageKey = when (loadType) {
                LoadType.REFRESH -> ""
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                     val lastItem = initial_response.next ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem
                }
            }
//state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
            //lastItem.id = initial_response.next

            val response = service.getData(pageKey)

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                   // appDatabase.postDao().clearAll()
                }
                val items =response.data.listIterator().next()
                Log.d("items", items.id)
                //itemsDao.insertAll(items)
            }

            return MediatorResult.Success(endOfPaginationReached = response.next.isNullOrEmpty())
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }
}
