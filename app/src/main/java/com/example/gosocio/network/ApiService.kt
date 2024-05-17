package com.example.gosocio.network

import com.example.gosocio.entities.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("feed/{next}")
    suspend fun getData(@Path("next") next: String?) : ApiResponse
}
