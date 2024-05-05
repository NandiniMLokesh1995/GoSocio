package com.example.gosocio.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gosocio.entities.Converter
import com.example.gosocio.entities.ItemsDao
import com.example.gosocio.entities.Items

@Database(entities = [Items::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun itemDao(): ItemsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration() // Strategy on how to handle migrations.
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}