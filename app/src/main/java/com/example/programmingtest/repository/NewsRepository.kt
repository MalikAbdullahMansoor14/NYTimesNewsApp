package com.example.programmingtest.repository

/**
 * @author Abdullah Mansoor
 * @Date 8/12/22
 *
 * Data source class for accessing API data
 */


import com.example.programmingtest.model.NewsResponse
import com.example.programmingtest.network.ApiClient
import retrofit2.Response

class NewsRepository() {
    suspend fun getMostPopularNewsByPeriod(period: Int, apiKey: String): Response<NewsResponse> =
        ApiClient.retrofitService.getMostPopularNewsByPeriod(period, apiKey)
}