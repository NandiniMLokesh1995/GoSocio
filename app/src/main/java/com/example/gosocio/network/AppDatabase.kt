package com.example.gosocio.network

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gosocio.entities.Converter
import com.example.gosocio.entities.Items
import com.example.gosocio.entities.ItemsDao

@Database(entities = [Items::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun itemDao(): ItemsDao
}