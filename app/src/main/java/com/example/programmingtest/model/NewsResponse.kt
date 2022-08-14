package com.example.programmingtest.model

/**
 * @author Abdullah Mansoor
 * @Date 8/13/22
 */

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<News>?
)

data class News(
    @SerializedName("uri")
    val uri: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("asset_id")
    val assetId: Long,
    @SerializedName("source")
    val source: String,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("section")
    val section: String,
    @SerializedName("subsection")
    val subsection: String,
    @SerializedName("nytdsection")
    val nytdsection: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("byline")
    val byline: String,
    @SerializedName("abstract")
    val abstract: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("adx_keywords")
    val adxKeywords: String,
    @SerializedName("media")
    val media: List<Media>?
) : Serializable

data class Media(
    @SerializedName("type")
    val type: String,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("media-metadata")
    val mediaMetaData: List<MetaData>?
) : Serializable

data class MetaData(
    val url: String,
    val format: String,
    val height: Int,
    val width: Int
) : Serializable