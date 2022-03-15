package com.example.nytimesarticle.main

import com.example.nytimesarticle.data.models.NYArticleResponse
import com.example.nytimesarticle.util.Resource

interface MainRepository {

    suspend fun getNYArticles(access_key: String): Resource<NYArticleResponse>
}