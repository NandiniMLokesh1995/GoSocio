package com.example.gosocio.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "items_table")
data class Items(
    @PrimaryKey
    @SerialName("id")
    val id : String,
    @SerialName("title")
    val title: String,
    @SerialName("thumbnail")
    val thumbnail : String,
    @SerialName("name")
    val name : String,
    @SerialName("username")
    val username : String,
    @SerialName("profile_pic")
    val profile_pic : String,
    @SerialName("dated")
    val dated : Long,
    @SerialName("type")
    val type : String
)
