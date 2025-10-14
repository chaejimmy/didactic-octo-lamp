package com.shourov.apps.pacedream.core.network.util

sealed class Resource<out T> {

    data class Success<out T>(val response: T) : Resource<T>()
    data class Error<out T>(val message: String?, val errorCode: Int? = -1000) : Resource<T>()
    data class Loading<out T>(val showLoading: Boolean) : Resource<T>()


}
