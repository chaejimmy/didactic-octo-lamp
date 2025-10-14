package com.shourov.apps.pacedream.core.network.model

import com.shourov.apps.pacedream.core.network.model.ApiResult.GenericError
import com.shourov.apps.pacedream.core.network.model.ApiResult.NetworkError
import com.shourov.apps.pacedream.core.network.model.ApiResult.Success

sealed class ApiResult<out T> {
    data class Success<out T>(val value: T) : ApiResult<T>()
    data class GenericError(val throwable: Throwable? = null) : ApiResult<Nothing>()
    object NetworkError : ApiResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[Response=$value]"
            is GenericError -> "GenericError[error=${throwable?.message}]"
            NetworkError -> "NetworkError"
        }
    }
}

inline fun <reified T> ApiResult<T>.doIfNetworkError(callback: () -> Unit) {
    if (this is NetworkError) {
        callback()
    }
}

inline fun <reified T> ApiResult<T>.doIfGenericError(
    callback: (throwable: Throwable?) -> Unit
) {
    if (this is GenericError) {
        callback(throwable)
    }
}

inline fun <reified T> ApiResult<T>.doIfSuccess(callback: (value: T) -> Unit) {
    if (this is Success) {
        callback(value)
    }
}

inline fun <reified T> List<ApiResult<T>>.doIfAnyNetworkError(
    callback: (result: NetworkError) -> Unit
) {
    val index = indexOfFirst { it is NetworkError }
    if (index >= 0) {
        callback(filterIsInstance<NetworkError>().first())
    }
}

inline fun <reified T> List<ApiResult<T>>.doIfAnyGenericError(
    callback: (result: GenericError) -> Unit
) {
    val index = indexOfFirst { it is GenericError }
    if (index >= 0) {
        callback(filterIsInstance<GenericError>().first())
    }
}
