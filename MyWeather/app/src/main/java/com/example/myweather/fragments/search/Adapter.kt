package com.example.myweather.fragments.search

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myweather.R
import com.example.myweather.data.City

class Adapter : ListAdapter<City, Adapter.WeatherViewHolder>(WeatherDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var cityName: TextView = itemView.findViewById(R.id.city_name)
        private var temperature: TextView = itemView.findViewById(R.id.city_temperature)
        private var weatherImg: ImageView = itemView.findViewById(R.id.weather_icon)

        fun bindTo(city: City) {
            cityName.text = city.name.toString()
            temperature.text = city.main?.temp.toString()
            val url = "http://openweathermap.org/img/wn/${city.weather[0].icon}@4x.png"

            Glide.with(weatherImg.context).load(url).into(weatherImg)
        }
    }

    class WeatherDiffCallback: DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem == newItem
        }
    }

    class MarginItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            with(outRect) {
                if (parent.getChildAdapterPosition(view) == 0) {
                    top = spaceSize
                }
                left = spaceSize
                right = spaceSize
                bottom = spaceSize
            }
        }
    }

}