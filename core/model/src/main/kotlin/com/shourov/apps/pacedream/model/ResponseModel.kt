package com.shourov.apps.pacedream.model


import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("current_observation")
    val currentObservation: CurrentObservation?,
    @SerializedName("forecasts")
    val forecasts: List<Forecast?>?,
    @SerializedName("location")
    val location: Location?
) {
    data class CurrentObservation(
        @SerializedName("astronomy")
        val astronomy: Astronomy?,
        @SerializedName("atmosphere")
        val atmosphere: Atmosphere?,
        @SerializedName("condition")
        val condition: Condition?,
        @SerializedName("pubDate")
        val pubDate: Int?,
        @SerializedName("wind")
        val wind: Wind?
    ) {
        data class Astronomy(
            @SerializedName("sunrise")
            val sunrise: String?,
            @SerializedName("sunset")
            val sunset: String?
        )

        data class Atmosphere(
            @SerializedName("humidity")
            val humidity: Int?,
            @SerializedName("pressure")
            val pressure: Double?,
            @SerializedName("visibility")
            val visibility: Int?
        )

        data class Condition(
            @SerializedName("code")
            val code: Int?,
            @SerializedName("temperature")
            val temperature: Int?,
            @SerializedName("text")
            val text: String?
        )

        data class Wind(
            @SerializedName("chill")
            val chill: Int?,
            @SerializedName("direction")
            val direction: String?,
            @SerializedName("speed")
            val speed: Int?
        )
    }

    data class Forecast(
        @SerializedName("code")
        val code: Int?,
        @SerializedName("date")
        val date: Int?,
        @SerializedName("day")
        val day: String?,
        @SerializedName("high")
        val high: Int?,
        @SerializedName("low")
        val low: Int?,
        @SerializedName("text")
        val text: String?
    )

    data class Location(
        @SerializedName("city")
        val city: String?,
        @SerializedName("country")
        val country: String?,
        @SerializedName("lat")
        val lat: Double?,
        @SerializedName("long")
        val long: Double?,
        @SerializedName("region")
        val region: String?,
        @SerializedName("timezone_id")
        val timezoneId: String?,
        @SerializedName("woeid")
        val woeid: Int?
    )
}