package com.example.myweather.data

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp")
    var temp: Float = 0f,

    @SerializedName("feels_like")
    var feels_like: Float = 0f,

    @SerializedName("temp_min")
    var temp_min: Float = 0f,

    @SerializedName("temp_max")
    var temp_max: Float = 0f,

    @SerializedName("pressure")
    var pressure: Float = 0f,

    @SerializedName("humidity")
    var humidity: Float = 0f
)
