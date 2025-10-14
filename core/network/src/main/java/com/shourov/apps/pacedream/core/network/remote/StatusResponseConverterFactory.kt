package com.shourov.apps.pacedream.core.network.remote

import com.shourov.apps.pacedream.model.response.base.StatusResponse
import com.squareup.moshi.JsonReader
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.asResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException
import java.lang.reflect.Type

class InvalidStatusException(message: String) : Exception(message)

/**
 * Denotes that the HTTP response body will be sent to the response body converter for converting
 * to type`, even if the key status is 0.
 * ```
 * {
 *   "status": 0,
 *   "code": 400,
 *   "message": "You account has been blocked.",
 *   "DATA": {
 *     "Blocked": 0
 *   }
 * }
 * ```
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ConvertStatusZero

class StatusResponseConverterFactory : Converter.Factory() {

    companion object {
        private val options = JsonReader.Options.of("status", "message")
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        if (getRawType(type).superclass != StatusResponse::class.java) return null
        val delegateConverter = retrofit.nextResponseBodyConverter<Any?>(this, type, annotations)

        return StatusConverter(delegate = delegateConverter)
//        return StatusConverter(
//            delegateConverter,
//            retrofit.responseBodyConverter<Any>(type, annotations).hashCode()
//        )
    }

    inner class StatusConverter(private val delegate: Converter<ResponseBody, Any>?) :
        Converter<ResponseBody, Any> {
        override fun convert(value: ResponseBody): Any? {
            var status = false
            var message: Any? = null
            var data: Any? = null

            try {
//                JsonReader.of(value.copy().source()).apply {
//                    beginObject()
//                    while (hasNext()) {
//                        when (selectName(options)) {
//                            0 -> status = nextBoolean()
//                            1 -> message = nextString()
//                            else -> {
//                                skipName()
//                                skipValue()
//                            }
//                        }
//                    }
//                    endObject()
//                }
                val reader = JsonReader.of(value.copy().source())

                reader.beginObject()
                while (reader.hasNext()) {
                    val name = reader.nextName()
                    when (name) {
                        "status" -> status = reader.nextBoolean()
                        "message" -> message = when (reader.peek()) {
                            JsonReader.Token.STRING -> reader.nextString()
                            JsonReader.Token.BEGIN_OBJECT -> {
                                reader.beginObject()
                                val messageObject = mutableMapOf<String, Any>()
                                while (reader.hasNext()) {
                                    messageObject[reader.nextName()] = reader.nextString()
                                }
                                reader.endObject()
                                messageObject
                            }

                            else -> null
                        }

                        "data" -> data =
                            if (reader.peek() == JsonReader.Token.NULL) reader.skipValue() else reader.nextString()

                        else -> reader.skipValue()
                    }
                }
                reader.endObject()
            } catch (t: Throwable) {
                Timber.e(t, "ResponseBody parsing failed.")
            }

            if (status) {
                return value.let { delegate?.convert(it) }
            }

            throw InvalidStatusException(
                message = when (message) {
                    is Map<*, *> -> message["error"] as? String ?: "Unknown error"
                    is String -> message
                    else -> "Unknown error"
                }
            )
        }
    }
}

@Throws(IOException::class)
private fun ResponseBody.copy(): ResponseBody {
    val source = source()
    source.request(Long.MAX_VALUE)
    val bufferedCopy = source.buffer.clone()
    return bufferedCopy.asResponseBody(contentType(), contentLength())
}
