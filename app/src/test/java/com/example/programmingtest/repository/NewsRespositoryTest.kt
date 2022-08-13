package com.example.programmingtest.repository

import com.example.programmingtest.network.ApiClient
import com.example.programmingtest.network.NewsApiService
import com.example.programmingtest.utils.Constants
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class NewsRepositoryTest {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    lateinit var newsRepository: NewsRepository
    lateinit var apiService: NewsApiService

    @Before
    fun setup() {
        apiService = ApiClient.getInstance().create(NewsApiService::class.java)
        newsRepository = NewsRepository()
    }

    @Test
    fun `Get most popular news with 1 day period, returns OK`() {
        runBlocking {
            val response = apiService.getMostPopularNewsByPeriod(1, Constants.API_KEY)
            assertThat(response.body()?.status, CoreMatchers.equalTo("OK"))
        }

    }
}
