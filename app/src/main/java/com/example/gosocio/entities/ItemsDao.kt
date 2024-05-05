package com.example.gosocio.entities

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items_table")
    fun getAllPosts(): PagingSource<Int, Items>

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: Items)*/

}
