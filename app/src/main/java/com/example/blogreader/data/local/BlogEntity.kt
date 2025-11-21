package com.example.blogreader.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blogs")
data class BlogEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val excerpt: String,
    val content: String,
    val link: String,
    val date: String,
    val imageUrl: String?,
    val page: Int,
    val timestamp: Long = System.currentTimeMillis()
)
