package com.example.gosocio.network

import com.example.gosocio.entities.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("feed/")
    suspend fun getData( @Query("") next: String):ApiResponse
}
