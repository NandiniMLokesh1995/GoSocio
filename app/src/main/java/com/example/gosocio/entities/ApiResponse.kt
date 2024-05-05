package com.example.gosocio.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerializedName("success")
    val success :Boolean,
    @SerializedName("data")
    val data : List<Items>,
    @SerializedName("next")
    val next : String
)
