package com.example.myweather.data

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("coord")
    var coord: Coord? = null,

    @SerializedName("main")
    var main: Main? = null,

    @SerializedName("dt")
    var dt: Float = 0f,

    @SerializedName("wind")
    var wind: Wind? = null,

    @SerializedName("sys")
    var sys: Sys? = null,

    @SerializedName("rain")
    var rain: Rain? = null,

    @SerializedName("weather")
    var weather: ArrayList<Weather> = ArrayList(),

    @SerializedName("clouds")
    var clouds: Clouds? = null,

    @SerializedName("cod")
    var cod: Float = 0f
)
