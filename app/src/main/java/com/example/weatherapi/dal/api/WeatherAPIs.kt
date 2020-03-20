package com.example.weatherapi.dal.api

import com.example.weatherapi.dal.models.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIs {
    @GET(value = "weather/?units=metric?lang=pl")
    fun getWeather(@Query("q") cityName: String, @Query("appid") apiKey: String): Call<Weather>
}