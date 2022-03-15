package com.example.nytimesarticle.data

import com.example.nytimesarticle.data.models.NYArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NYArticleApi {

    @GET("svc/mostpopular/v2/viewed/7.json")
    suspend fun getNYArticles(@Query("api_key") access_key: String): Response<NYArticleResponse>
}