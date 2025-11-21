package com.example.blogreader.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface BlogApiService {
    @GET("posts")
    suspend fun getBlogs(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
        @Query("_embed") embed: Boolean = true
    ): List<BlogDto>
}