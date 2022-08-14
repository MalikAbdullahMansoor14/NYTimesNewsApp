package com.example.programmingtest.viewmodel
/**
 * @author Abdullah Mansoor
 * @Date 8/12/22
 */


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.programmingtest.repository.NewsRepository

class ViewModelProviderFactory(
    val repository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}