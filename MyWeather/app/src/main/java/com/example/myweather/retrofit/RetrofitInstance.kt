package com.example.myweather.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private var retrofit: Retrofit? = null

    val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val httpClient = OkHttpClient.Builder().addInterceptor(logging)

    fun getClient(apiUrl: String): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }

        return retrofit
    }
}
