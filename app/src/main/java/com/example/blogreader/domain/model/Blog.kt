package com.example.blogreader.domain.model

data class Blog(
    val id: Int,
    val title: String,
    val excerpt: String,
    val content: String,
    val link: String,
    val date: String,
    val imageUrl: String?,
    val formattedDate: String
)