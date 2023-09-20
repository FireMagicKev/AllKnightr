package com.example.allknightrapp.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class GenresResponse : ArrayList<GenresResponseItem>()

@Serializable
data class GenresResponseItem(
    @SerialName("checksum")
    val checksum: String = "",
    @SerialName("created_at")
    val createdAt: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("updated_at")
    val updatedAt: Int = 0,
    @SerialName("url")
    val url: String = ""
)