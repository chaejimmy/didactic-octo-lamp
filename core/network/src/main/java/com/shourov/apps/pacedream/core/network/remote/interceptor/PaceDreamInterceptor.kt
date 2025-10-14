package com.shourov.apps.pacedream.core.network.remote.interceptor

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response

class PaceDreamInterceptor(private val authorization: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder().apply {
            addHeader("Authorization", authorization)
        }

        val body = request.body as? FormBody
        if (body != null) {
            val params = buildMap<String, String> {
                putAll(body.params())
            }
            requestBuilder.post(params.toFromBody())
        }

        return chain.proceed(requestBuilder.build())
    }
}
