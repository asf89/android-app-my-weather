package com.example.myweather.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.myweather.retrofit.RetrofitInstance
import com.example.myweather.weather.WeatherResponse

class ApiClient {

    private val apiUrl = "http://api.openweathermap.org"

    fun getApiInterface(): ApiInterface? {
        return RetrofitInstance().getClient(apiUrl)?.create(ApiInterface::class.java)
    }

    interface ApiInterface {
        @GET("/data/2.5/find")
        fun getSearchCityWeatherData(
            @Query("q") city: String?,
            @Query("units") unit: String?,
            @Query("lang") language: String?,
            @Query("appid") apiKey: String?
        ): Call<WeatherResponse>
    }
}