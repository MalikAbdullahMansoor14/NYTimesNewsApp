package com.example.programmingtest.network

import com.example.programmingtest.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApiService {

    @GET("all-sections/{period}.json")
    suspend fun getMostPopularNewsByPeriod(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): Response<NewsResponse>

}