package com.shourov.apps.pacedream.core.network.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ProvideKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder().apply {
            addHeader("Accept", "*/*")
        }

        return chain.proceed(requestBuilder.build())
    }
}