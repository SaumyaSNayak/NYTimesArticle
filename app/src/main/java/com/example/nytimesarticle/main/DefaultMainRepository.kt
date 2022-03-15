package com.example.nytimesarticle.main

import com.example.nytimesarticle.data.NYArticleApi
import com.example.nytimesarticle.data.models.NYArticleResponse
import com.example.nytimesarticle.util.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: NYArticleApi
) : MainRepository {

    override suspend fun getNYArticles(access_key: String): Resource<NYArticleResponse> {
        return try {
            val response = api.getNYArticles(access_key)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch(e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}