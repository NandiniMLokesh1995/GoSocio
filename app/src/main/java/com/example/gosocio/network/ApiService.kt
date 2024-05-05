package com.example.gosocio.network

import com.example.gosocio.entities.ApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET
    suspend fun getData( next: String):ApiResponse
}
