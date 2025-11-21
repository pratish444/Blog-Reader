package com.example.blogreader.presentation.bloglist.blogdetail

import com.example.blogreader.domain.model.Blog

data class BlogDetailState(
    val blog: Blog? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)