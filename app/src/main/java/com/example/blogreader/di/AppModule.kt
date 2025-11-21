package com.example.blogreader.di

import android.content.Context
import androidx.room.Room
import com.example.blogreader.data.local.BlogDao
import com.example.blogreader.data.local.BlogDatabase
import com.example.blogreader.data.remote.BlogApiService
import com.example.blogreader.data.repository.BlogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://blog.vrid.in/wp-json/wp/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBlogApiService(retrofit: Retrofit): BlogApiService {
        return retrofit.create(BlogApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBlogDatabase(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            "blog_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBlogDao(database: BlogDatabase): BlogDao {
        return database.blogDao()
    }

    @Provides
    @Singleton
    fun provideBlogRepository(
        apiService: BlogApiService,
        blogDao: BlogDao
    ): BlogRepository {
        return BlogRepository(apiService, blogDao)
    }
}
