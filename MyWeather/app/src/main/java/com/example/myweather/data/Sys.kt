package com.example.myweather.data

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country")
    var country: String? = null
)
