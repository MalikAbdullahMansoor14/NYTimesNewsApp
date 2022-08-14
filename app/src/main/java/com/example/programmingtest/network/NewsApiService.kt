package com.example.programmingtest.network
/**
 * @author Abdullah Mansoor
 * @Date 8/12/22
 */

import com.example.programmingtest.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApiService {

    // Most viewed list //

    @GET("all-sections/{period}.json")
    suspend fun getMostPopularNewsByPeriod(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): Response<NewsResponse>

}