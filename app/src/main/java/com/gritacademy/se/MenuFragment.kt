package com.gritacademy.se

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class MenuFragment : Fragment(R.layout.fragment_menu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weatherButton = view.findViewById<Button>(R.id.weatherButton)
        val historyButton = view.findViewById<Button>(R.id.historyButton)

        weatherButton.setOnClickListener(View.OnClickListener { v: View? -> openWeather() })

        historyButton.setOnClickListener(View.OnClickListener { v: View? -> openHistory() })
    }

    private fun openHistory() {
        requireActivity().getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, HistoryFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun openWeather() {
        requireActivity().getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, WeatherFragment())
            .addToBackStack(null)
            .commit()
    }
}