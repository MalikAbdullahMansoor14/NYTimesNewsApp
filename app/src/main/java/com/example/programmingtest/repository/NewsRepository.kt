package com.example.programmingtest.repository

import com.example.programmingtest.model.NewsResponse
import com.example.programmingtest.network.ApiClient
import retrofit2.Response

class NewsRepository() {
    suspend fun getMostPopularNewsByPeriod(period: Int, apiKey: String): Response<NewsResponse> =
        ApiClient.retrofitService.getMostPopularNewsByPeriod(period, apiKey)
}