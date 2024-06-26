package com.example.gosocio.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemViewModelFactory(private val imageRepository: ItemRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(imageRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}