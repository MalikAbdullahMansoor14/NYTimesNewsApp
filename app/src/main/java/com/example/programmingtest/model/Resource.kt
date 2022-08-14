package com.example.programmingtest.model

/**
 * @author Abdullah Mansoor
 * @Date 8/12/22
 *
 * This class controls the data response to manage different states with UI using sealed class
 */

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}