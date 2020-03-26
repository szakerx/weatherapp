package com.example.weatherapi.ui.main

import androidx.lifecycle.ViewModel
import com.example.weatherapi.dal.api.Remote

class MainViewModel : ViewModel() {
    private val remote: Remote = Remote()

    fun getWeatherData(cityName: String) = remote.getWeather(cityName)
}
