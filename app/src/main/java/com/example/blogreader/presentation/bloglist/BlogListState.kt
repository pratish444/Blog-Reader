package com.example.blogreader.presentation.bloglist

import com.example.blogreader.domain.model.Blog

data class BlogListState(
    val blogs: List<Blog> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 1,
    val hasMorePages: Boolean = true,
    val isOffline: Boolean = false
)