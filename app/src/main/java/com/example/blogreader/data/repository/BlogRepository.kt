package com.example.blogreader.data.repository

import android.text.Html
import com.example.blogreader.data.local.BlogDao
import com.example.blogreader.data.local.BlogEntity
import com.example.blogreader.data.remote.BlogApiService
import com.example.blogreader.domain.model.Blog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class BlogRepository @Inject constructor(
    private val api: BlogApiService,
    private val dao: BlogDao
) {
    fun getBlogs(): Flow<List<Blog>> {
        return dao.getAllBlogs().map { entities ->
            entities.map { it.toBlog() }
        }
    }

    suspend fun fetchAndCacheBlogs(page: Int): Result<Unit> {
        return try {
            val blogs = api.getBlogs(perPage = 10, page = page, embed = true)
            val entities = blogs.map { dto ->
                BlogEntity(
                    id = dto.id,
                    title = Html.fromHtml(dto.title.rendered, Html.FROM_HTML_MODE_LEGACY).toString(),
                    excerpt = Html.fromHtml(dto.excerpt.rendered, Html.FROM_HTML_MODE_LEGACY).toString(),
                    content = dto.content.rendered,
                    link = dto.link,
                    date = dto.date,
                    imageUrl = dto.embedded?.featuredMedia?.firstOrNull()?.sourceUrl,
                    page = page
                )
            }
            dao.insertBlogs(entities)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBlogById(id: Int): Blog? {
        return dao.getBlogById(id)?.toBlog()
    }

    suspend fun clearOldCache() {
        val threshold = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000) // 7 days
        dao.deleteOldBlogs(threshold)
    }

    private fun BlogEntity.toBlog(): Blog {
        val formattedDate = try {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            val date = parser.parse(this.date)
            date?.let { formatter.format(it) } ?: this.date
        } catch (e: Exception) {
            this.date
        }

        return Blog(
            id = id,
            title = title,
            excerpt = excerpt,
            content = content,
            link = link,
            date = date,
            imageUrl = imageUrl,
            formattedDate = formattedDate
        )
    }
}
