package com.example.blogreader.data.remote

import com.google.gson.annotations.SerializedName

data class BlogDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("title")
    val title: TitleDto,

    @SerializedName("content")
    val content: ContentDto,

    @SerializedName("excerpt")
    val excerpt: ExcerptDto,

    @SerializedName("link")
    val link: String,

    @SerializedName("featured_media")
    val featuredMedia: Int,

    @SerializedName("_embedded")
    val embedded: EmbeddedDto? = null
)

data class TitleDto(
    @SerializedName("rendered")
    val rendered: String
)

data class ContentDto(
    @SerializedName("rendered")
    val rendered: String
)

data class ExcerptDto(
    @SerializedName("rendered")
    val rendered: String
)

data class EmbeddedDto(
    @SerializedName("wp:featuredmedia")
    val featuredMedia: List<MediaDto>? = null
)

data class MediaDto(
    @SerializedName("source_url")
    val sourceUrl: String
)