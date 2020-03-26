package com.example.weatherapi.dal.models

import java.io.Serializable

data class Weather(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: Wind
): Serializable {
    override fun toString(): String {
        return "$name this is the name"
    }
}