package com.example.myweather.data

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("h3")
    var h3: Float = 0f
)
