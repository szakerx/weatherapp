package com.example.weatherapi.dal.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapi.dal.models.Weather
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Remote {
    private val baseUrl = "https://api.openweather.org/data/2.5/"
    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
    private val weaterService = retrofit.create(WeatherAPIs::class.java)
    private val apiKey = "86191e6efb0189903aacb148e56ea2a3"

    fun getWeather(cityName: String): LiveData<Weather> {
        val responseWeather = MutableLiveData<Weather>()
        val call = weaterService.getWeather(cityName, this.apiKey)

        call.enqueue(object: Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if (response.isSuccessful) {
                    responseWeather.value = response.body()
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
            }
        })
        return responseWeather
    }
}