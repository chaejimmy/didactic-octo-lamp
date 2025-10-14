package com.shourov.apps.pacedream.core.network.model.auth


import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class SignupRequestModel(
    val dob: String?,
    val firstName: String?,
    val gender: String?,
    val lastName: String?,
    val password: String?,
    val phoneNumber: String?
)

object SignupRequestModelSerializer : KSerializer<SignupRequestModel> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SignupRequestModel") {
        element<String>("dob")
        element<String>("firstName")
        element<String>("gender")
        element<String>("lastName")
        element<String>("password")
        element<String>("phoneNumber")
    }

    override fun serialize(encoder: Encoder, value: SignupRequestModel) {
        val output = encoder.beginStructure(descriptor)
        output.encodeStringElement(descriptor, 0, value.dob ?: "")
        output.encodeStringElement(descriptor, 1, value.firstName ?: "")
        output.encodeStringElement(descriptor, 2, value.gender ?: "")
        output.encodeStringElement(descriptor, 3, value.lastName ?: "")
        output.encodeStringElement(descriptor, 4, value.password ?: "")
        output.encodeStringElement(descriptor, 5, value.phoneNumber ?: "")
        output.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): SignupRequestModel {
        val input = decoder.beginStructure(descriptor)
        var dob: String? = null
        var firstName: String? = null
        var gender: String? = null
        var lastName: String? = null
        var password: String? = null
        var phoneNumber: String? = null

        loop@ while (true) {
            when (val i = input.decodeElementIndex(descriptor)) {
                CompositeDecoder.DECODE_DONE -> break@loop
                0 -> dob = input.decodeStringElement(descriptor, i)
                1 -> firstName = input.decodeStringElement(descriptor, i)
                2 -> gender = input.decodeStringElement(descriptor, i)
                3 -> lastName = input.decodeStringElement(descriptor, i)
                4 -> password = input.decodeStringElement(descriptor, i)
                5 -> phoneNumber = input.decodeStringElement(descriptor, i)
                else -> throw SerializationException("Unknown index: $i")
            }
        }
        input.endStructure(descriptor)

        return SignupRequestModel(dob, firstName, gender, lastName, password, phoneNumber)
    }
}