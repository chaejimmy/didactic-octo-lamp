package com.shourov.apps.pacedream.core.network.remote.interceptor

import okhttp3.FormBody

fun FormBody.params(): Map<String, String> = buildMap {
    for (i in 0 until this@params.size) {
        put(name(i), value(i))
    }
}

fun Map<String, String>.toFromBody(): FormBody {
    val formBodyBuilder = FormBody.Builder()
    forEach {
        formBodyBuilder.add(it.key, it.value)
    }
    return formBodyBuilder.build()
}
