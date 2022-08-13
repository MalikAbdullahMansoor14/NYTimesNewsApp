package com.example.programmingtest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.programmingtest.CoroutineRule
import com.example.programmingtest.getOrAwaitValueTest
import com.example.programmingtest.network.ApiClient
import com.example.programmingtest.network.NewsApiService
import com.example.programmingtest.repository.NewsRepository
import com.example.programmingtest.viewmodel.NewsViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = CoroutineRule()

    private lateinit var viewModel: NewsViewModel
    lateinit var apiService: NewsApiService

    @Before
    fun setup() {
        apiService = ApiClient.getInstance().create(NewsApiService::class.java)
        viewModel = NewsViewModel(NewsRepository())
    }

    @Test
    fun `Get most popular news with 1 day`() {
        runBlocking {
            viewModel.getMostPopularNewsByPeriod(1)
            val value = viewModel.mostPopularNewsListLiveData.getOrAwaitValueTest()
            println(value)
            assertEquals(value.data?.status, CoreMatchers.equalTo("OK"))
        }

    }
}
