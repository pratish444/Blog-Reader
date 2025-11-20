package com.example.blogreader.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogEntity::class], version = 1, exportSchema = false)
abstract class BlogDatabase : RoomDatabase() {
    abstract fun blogDao(): BlogDao
}