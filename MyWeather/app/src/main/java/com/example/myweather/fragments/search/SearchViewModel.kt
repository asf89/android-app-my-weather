package com.example.myweather.fragments.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.data.City

class SearchViewModel: ViewModel() {
    var weatherList = MutableLiveData<ArrayList<City>>()
    var tempList = ArrayList<City>()

    fun updateCityList(cityList: ArrayList<City>) {
        tempList.clear()
        tempList = cityList
        weatherList.value = tempList
    }

}