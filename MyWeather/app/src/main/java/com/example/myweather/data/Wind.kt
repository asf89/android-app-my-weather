package com.example.myweather.data

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed")
    var speed: Float = 0f,

    @SerializedName("deg")
    var deg: Float = 0f
)
