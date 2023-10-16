package com.example.crudapplication.data.model

import com.google.gson.annotations.SerializedName

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    @SerializedName("body") val textBody: String
)
