package com.example.myweather.data

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lon")
    var lon: Float = 0f,

    @SerializedName("lat")
    var lat: Float = 0f
)
