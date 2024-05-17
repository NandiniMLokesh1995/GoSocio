package com.example.gosocio.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://fdb695ae-17be-4dca-8afa-875fa60e8a4f.mock.pstmn.io/"
    //"https://b8e26888-a48d-429d-8820-c9e346c8b993.mock.pstmn.io/feed/"

    private val client = OkHttpClient.Builder().apply {
        // Add logging interceptor
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }.build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
