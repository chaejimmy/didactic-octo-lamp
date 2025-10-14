package com.shourov.apps.pacedream.core.network.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class TokenExpiredInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == 401 || response.code == 403) {
            synchronized(this) {
//                ProcessLifecycleOwner.get().lifecycleScope.launch {
//                    EventBus.sendEvent(Event.Logout)
//                }
            }
        }
        return response
    }
}
