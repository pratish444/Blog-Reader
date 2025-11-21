package com.example.blogreader.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BlogDao {
    @Query("SELECT * FROM blogs ORDER BY date DESC")
    fun getAllBlogs(): Flow<List<BlogEntity>>

    @Query("SELECT * FROM blogs WHERE page = :page ORDER BY date DESC")
    fun getBlogsByPage(page: Int): Flow<List<BlogEntity>>

    @Query("SELECT * FROM blogs WHERE id = :id")
    suspend fun getBlogById(id: Int): BlogEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogs(blogs: List<BlogEntity>)

    @Query("DELETE FROM blogs WHERE timestamp < :threshold")
    suspend fun deleteOldBlogs(threshold: Long)

    @Query("SELECT MAX(page) FROM blogs")
    suspend fun getMaxPage(): Int?
}