package com.shourov.apps.pacedream.core.network.util


import com.shourov.apps.pacedream.core.network.util.Resource.Error
import com.shourov.apps.pacedream.core.network.util.Resource.Loading
import com.shourov.apps.pacedream.core.network.util.Resource.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import retrofit2.Response


fun <T> result(callApi: suspend () -> Response<T>): Flow<Resource<T?>> {
    return flow<Resource<T?>> {
        emit(Loading(true))
        delay(500)
        try {
            val response = callApi.invoke()
            response.let {

                if (response.isSuccessful) {
                    emit(Success(response.body()))

                } else {
                    val errorBody = response.errorBody()?.string()
                    var errorMessage = ""
                    val errorMessageJSON = try {
                        val jsonResponse = JSONObject(errorBody.toString()).getString("message")
                        val jsonObject = JSONObject(jsonResponse)
                        errorMessage = jsonObject.getString("error")
                    } catch (e: Exception) {
                        "Unknown error occurred"
                    }
                    emit(Error(errorMessage, response.code()))
                }
            }
            delay(500)
            emit(Loading(false))

        } catch (t: Exception) {
            val errorMsg = t.message
            emit(Error(errorMsg))
            emit(Loading(false))

        }
    }.flowOn(Dispatchers.IO)
}

