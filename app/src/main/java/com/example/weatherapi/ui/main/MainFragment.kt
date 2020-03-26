package com.example.weatherapi.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.weatherapi.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var button: MaterialButton
    private lateinit var inputText: TextInputEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view =  inflater.inflate(R.layout.main_fragment, container, false)
        button = view.confirm_button
        inputText = view.main_city_input
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        button.setOnClickListener{
            getCurrentWeather()
        }
    }

    private fun getCurrentWeather() {
        val textFieldValue = inputText.text.toString()
        viewModel.getWeatherData(textFieldValue).observe(viewLifecycleOwner, Observer { response ->
            if (response.status) {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToResponse(response.weather!!))
            } else {
                val snack = Snackbar.make(view!!, "Invalid city name", Snackbar.LENGTH_SHORT)
                val view = snack.view
                val tv = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                tv.textAlignment = View.TEXT_ALIGNMENT_CENTER;
                snack.show()
            }
        })
    }

}
