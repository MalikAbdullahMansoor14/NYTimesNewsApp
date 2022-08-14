package com.example.programmingtest.network

/**
 * @author Abdullah Mansoor
 * @Date 8/12/22
 *
 * client class for making http call using retrofit
 */

import com.example.programmingtest.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {




    fun getInstance(): Retrofit {

        var mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)

            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                chain.proceed(newRequest.build())
            }
            .build()


        var retrofit: Retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
        return retrofit
    }

    // creating service

    val retrofitService: NewsApiService by lazy {
        getInstance().create(NewsApiService::class.java)
    }

}