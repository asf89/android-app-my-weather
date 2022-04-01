package com.example.myweather.weather

import com.example.myweather.data.City
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("list")
    var responseList: ArrayList<City> = ArrayList()
)
