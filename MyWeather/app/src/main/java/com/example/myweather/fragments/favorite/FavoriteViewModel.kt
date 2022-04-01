package com.example.myweather.fragments.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteViewModel: ViewModel() {

    private val _favorites = MutableLiveData("List of favorites")

    fun getFavorites(): LiveData<String> {
        return _favorites
    }
}