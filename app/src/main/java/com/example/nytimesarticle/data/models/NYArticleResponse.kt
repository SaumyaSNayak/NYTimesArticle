package com.example.nytimesarticle.data.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class NYArticleResponse(
    @SerializedName("results")
    val contents: ArrayList<Content>
)