package com.example.programmingtest.viewmodel

import androidx.lifecycle.*
import com.example.programmingtest.model.NewsResponse
import com.example.programmingtest.model.Resource
import com.example.programmingtest.repository.NewsRepository
import com.example.programmingtest.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class NewsViewModel(
    val repository: NewsRepository
) : ViewModel() {

    // most popular news listing
    private val _mostPopularNewsList = MutableLiveData<Resource<NewsResponse>>()
    var mostPopularNewsListLiveData: LiveData<Resource<NewsResponse>>

    fun setNewsMutableLiveData(result: Resource<NewsResponse>) {
        _mostPopularNewsList.postValue(result)
    }

    init {
        mostPopularNewsListLiveData = _mostPopularNewsList
    }

    fun getMostPopularNewsByPeriod(period: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                setNewsMutableLiveData(Resource.Loading())
                val response: Response<NewsResponse> =
                    repository.getMostPopularNewsByPeriod(period, Constants.API_KEY)
                if (response.isSuccessful) {
                    response.body()?.let {
                        setNewsMutableLiveData(Resource.Success(it))
                    }
                } else
                    setNewsMutableLiveData(Resource.Error(response.message()))

            } catch (e: Exception) {
                setNewsMutableLiveData(Resource.Error(e.message.orEmpty()))
            }
        }
    }

}