package com.example.gosocio.di

import com.example.gosocio.network.ApiService
import com.example.gosocio.network.AppDatabase
import com.example.gosocio.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun providesRepository(appDatabase: AppDatabase, apiService: ApiService) :ItemRepository{
        return ItemRepository(appDatabase, apiService)
    }
}