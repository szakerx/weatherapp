package com.example.weatherapi.ui.response

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs

import com.example.weatherapi.R
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.response_fragment.view.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class Response : Fragment() {

    private lateinit var cityName: MaterialTextView
    private lateinit var description: MaterialTextView
    private lateinit var temperature: MaterialTextView
    private lateinit var pressure: MaterialTextView
    private lateinit var sunrise: MaterialTextView
    private lateinit var sunset: MaterialTextView
    private lateinit var dateTime: MaterialTextView
    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.response_fragment, container, false)
        cityName = view.response_city_name
        description = view.response_description
        temperature = view.response_temperature
        pressure = view.respone_pressure
        sunrise = view.response_sunrise
        sunset = view.response_sunset
        dateTime = view.response_date
        image = view.response_weather_icon
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args: ResponseArgs by navArgs()
        cityName.text = args.weather.name
        description.text = args.weather.weather.first().description.capitalize()
        temperature.text = String.format("%.2f°C", (args.weather.main.temp - 273.15))
        pressure.text = args.weather.main.pressure.toString() + " hPa"
        val simpleDateFormatter = SimpleDateFormat("HH:mm", Locale("PL"))
        sunrise.text =
            simpleDateFormatter.format(Date.from(Instant.ofEpochSecond(args.weather.sys.sunrise.toLong())))
        sunset.text =
            simpleDateFormatter.format(Date.from(Instant.ofEpochSecond(args.weather.sys.sunset.toLong())))
        dateTime.text = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm", Locale("pl-PL")))
        when (args.weather.weather.first().description) {
            "clear sky" -> image.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_weather_sunny
                )
            )
            "few clouds" -> image.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_weather_partly_cloudy
                )
            )
            "scattered clouds" -> image.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_weather_cloudy
                )
            )
            "broken clouds" -> image.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_weather_cloudy
                )
            )
            "shower rain" -> image.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_weather_pouring
                )
            )
            "rain" -> image.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_weather_rainy
                )
            )
            "thunderstorm" -> image.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_weather_lightning_rainy
                )
            )
            "snow" -> image.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_weather_snowy_heavy
                )
            )
            "mist" -> image.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_weather_fog
                )
            )

        }
    }

}
