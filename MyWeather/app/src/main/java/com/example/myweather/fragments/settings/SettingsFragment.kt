package com.example.myweather.fragments.settings

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myweather.R
import com.example.myweather.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {

    private val settingsViewModel: SettingsViewModel by activityViewModels()
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val settingsView: View = binding.root

        preferences = requireActivity()
            .getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        editor = preferences.edit()

        setListsInViewModel()

        binding.radioGroupTempUnit.check(settingsViewModel.temp.value!!)
        binding.langGroup.check(settingsViewModel.lang.value!!)

        binding.saveButton.setOnClickListener {
            savePreferences()
        }

        return settingsView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun savePreferences() {
        val temp = binding.radioGroupTempUnit
        val lang = binding.langGroup

        when (temp.checkedRadioButtonId) {

            R.id.celsius_button -> {
                settingsViewModel.changeTemp(R.id.celsius_button)
                editor.apply {
                    putString("temperature_unit", "metric")
                    apply()
                }
            }

            R.id.fahrenheit_button -> {
                settingsViewModel.changeTemp(R.id.fahrenheit_button)
                editor.apply {
                    putString("temperature_unit", "imperial")
                    apply()
                }
            }

        }

        when (lang.checkedRadioButtonId) {
            R.id.lang_en -> {
                settingsViewModel.changeLang(R.id.lang_en)
                editor.apply {
                    putString("language", "en")
                    apply()
                }
            }

            R.id.lang_pt -> {
                settingsViewModel.changeLang(R.id.lang_pt)
                editor.apply {
                    putString("language", "pt_br")
                    apply()
                }
            }
        }
    }

    private fun setListsInViewModel() {
        val preferences: SharedPreferences = requireActivity()
            .getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val temp = preferences.getString("temperature_unit", null)
        val lang = preferences.getString("language", null)
        var tempId = 0
        var langId = 0

        if (temp.toString() == "metric") {
            tempId = binding.celsiusButton.id
        } else if (temp.toString() == "imperial") {
            tempId = binding.fahrenheitButton.id
        }

        if (lang.toString() == "en") {
            langId = binding.langEn.id
        } else if (lang.toString() == "pt_br") {
            langId = binding.langPt.id
        }

        settingsViewModel.setLists(tempId, langId)
    }

}