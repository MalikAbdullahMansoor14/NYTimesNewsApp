package com.example.programmingtest

import androidx.lifecycle.viewModelScope
import com.example.programmingtest.model.NewsResponse
import com.example.programmingtest.model.Resource
import com.example.programmingtest.network.ApiClient
import com.example.programmingtest.network.NewsApiService
import com.example.programmingtest.repository.NewsRepository
import com.example.programmingtest.viewmodel.NewsViewModel
import com.example.programmingtest.utils.Constants
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
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
            MatcherAssert.assertThat(response.body()?.status, CoreMatchers.equalTo("OK"))
        }

    }

    @Test
    fun `Get most popular news with 7 days period, returns OK`() {
        runBlocking {
            val response = apiService.getMostPopularNewsByPeriod(7, Constants.API_KEY)
            MatcherAssert.assertThat(response.body()?.status, CoreMatchers.equalTo("OK"))
        }

    }

    @Test
    fun `Get most popular news with 7 days period, returns at least one news`() {
        runBlocking {
            val response = apiService.getMostPopularNewsByPeriod(7, Constants.API_KEY)
            Assert.assertNotNull(response.body()?.results)
        }

    }

    @Test
    fun `Get most popular news with 30 days period, returns OK`() {
        runBlocking {
            val response = apiService.getMostPopularNewsByPeriod(30, Constants.API_KEY)
            MatcherAssert.assertThat(response.body()?.status, CoreMatchers.equalTo("OK"))
//            assertNotNull(response.body()?.results)
        }

    }

    @Test
    fun testRetrofitInstance() {
        //Get an instance of Retrofit
        val instance: Retrofit = ApiClient.getInstance()
        //Assert that, Retrofit's base url matches to our BASE_URL
        assert(instance.baseUrl().toUrl().toString() == Constants.BASE_URL)
    }

    private lateinit var viewModel: NewsViewModel

    @Test
    fun isErrorEmittingInViewModel() {
        viewModel = NewsViewModel(NewsRepository())
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.setNewsMutableLiveData(Resource.Error("Error"))
            viewModel.mostPopularNewsListLiveData.getOrAwaitValueTest {
                assertEquals(viewModel.mostPopularNewsListLiveData.value?.message, "Error")
            }
        }

    }

    @Test
    fun isSuccessEmittingInViewModel() {
        viewModel = NewsViewModel(NewsRepository())
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.setNewsMutableLiveData(Resource.Success(NewsResponse(
                status = "OK",
                copyright = "",
                numResults = 10,
                results = null
            )))

            viewModel.mostPopularNewsListLiveData.getOrAwaitValueTest {
                assertEquals(viewModel.mostPopularNewsListLiveData.value?.data?.status, "OK")
            }
        }

    }


}