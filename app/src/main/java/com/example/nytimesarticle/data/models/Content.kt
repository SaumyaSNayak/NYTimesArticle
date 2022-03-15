package com.example.nytimesarticle.data.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class Content(
    @SerializedName("id")
    val id: String,

    @SerializedName("uri")
    val uri: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("asset_id")
    val asset_id: String,

    @SerializedName("source")
    val source: String,

    @SerializedName("published_date")
    val published_date: String,

    @SerializedName("section")
    val section: String,

    @SerializedName("byline")
    val byline: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("abstract")
    val abstract: String
)