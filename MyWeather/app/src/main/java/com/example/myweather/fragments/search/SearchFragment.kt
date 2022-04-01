package com.example.myweather.fragments.search

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweather.R
import com.example.myweather.api.ApiClient
import com.example.myweather.databinding.FragmentSearchBinding
import com.example.myweather.weather.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment: Fragment() {

    private lateinit var adapter: Adapter
    private val searchViewModel: SearchViewModel by activityViewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val searchView: View = binding.root
        val recyclerView = binding.recyclerView
        adapter = Adapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        searchViewModel.weatherList.observe(requireActivity(), {
            adapter.submitList(it)
        })

        binding.searchButton.setOnClickListener {
            searchWeather()
        }
        return searchView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun searchWeather() {
        val context = requireActivity().applicationContext
        if (!isInternetAvailable(context)) {
            Toast
                .makeText(
                    requireActivity().applicationContext,
                    "No internet connection",
                    Toast.LENGTH_SHORT
                )
                .show()
            return
        }

        val apiClient = ApiClient().getApiInterface()
        val preferences: SharedPreferences = requireActivity()
            .getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val unit = preferences.getString("temperature_unit", null)
        val lang = preferences.getString("language", null)
        val city = binding.searchText.text.toString()
        binding.progressBar.visibility = View.VISIBLE
        val apiKey = resources.getString(R.string.api_key)

        val myApi = apiClient?.getSearchCityWeatherData(city, unit, lang, apiKey)
        myApi?.enqueue(object : Callback<WeatherResponse?> {
            override fun onResponse(
                call: Call<WeatherResponse?>,
                response: Response<WeatherResponse?>
            ) {
                if (response.body() != null) {
                    val weatherResponse: WeatherResponse = response.body()!!
                    searchViewModel.updateCityList(weatherResponse.responseList)
                    adapter.notifyDataSetChanged()
                    binding.progressBar.visibility = View.GONE
                    Log.i("list", searchViewModel.weatherList.value.toString())
                }
            }

            override fun onFailure(call: Call<WeatherResponse?>, t: Throwable) {
                Toast
                    .makeText(getContext(), "No result found", Toast.LENGTH_SHORT)
                    .show()
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    @Suppress("DEPRECATION")
    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        cm.getNetworkCapabilities(cm.activeNetwork)?.run {
            result = when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }

        return result
    }

    private operator fun Unit.not(): Boolean {
        return false
    }
}


