package com.example.myweather.fragments.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.R

class SettingsViewModel: ViewModel() {
    var temp = MutableLiveData<Int>()
    var lang = MutableLiveData<Int>()

    fun setLists(tempId: Int, langId: Int) {
        if (tempId == 0) {
            temp.apply {
                value = R.id.celsius_button
            }
        } else {
            changeTemp(tempId)
        }

        if (langId == 0) {
            lang.apply {
                value = R.id.lang_pt
            }
        } else {
            changeLang(langId)
        }

    }
    fun changeTemp(tempId: Int) {
        temp.value = tempId
    }

    fun changeLang(langId: Int) {
        lang.value = langId
    }
}