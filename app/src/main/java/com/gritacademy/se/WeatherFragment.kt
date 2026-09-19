package com.gritacademy.se

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherFragment : Fragment(R.layout.fragment_weather) {
    //API KEY
    private val API_KEY = "76ed108824c68079af4e65ae7ed839e3"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weatherHumidity = view.findViewById<TextView>(R.id.weatherHumidity)
        val weatherWind = view.findViewById<TextView>(R.id.weatherWind)
        val weatherTemp = view.findViewById<TextView>(R.id.weatherTemp)
        val searchCity = view.findViewById<EditText>(R.id.searchCity)
        val searchCityButton = view.findViewById<Button>(R.id.searchCityButton)

        //Gets information about city when button is pressed
        searchCityButton.setOnClickListener(View.OnClickListener { v: View? ->
            val city = searchCity.getText().toString()
            getWeather(city, weatherTemp, weatherWind, weatherHumidity)
        })
    }

    private fun getWeather(
        city: String,
        weatherTemp: TextView,
        weatherWind: TextView,
        weatherHumidity: TextView
    ) {
        val db = FirebaseFirestore.getInstance()

        //Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Connection between Retrofit and REST API
        val weatherAPI = retrofit.create<WeatherAPI>(WeatherAPI::class.java)

        val call = weatherAPI.getWeather(
            city,
            API_KEY,
            "metric"
        )

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                //Checks if there is an error incase user typed a city that does not exist

                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(requireContext(), "City not found", Toast.LENGTH_SHORT).show()
                    return
                }

                //Displays data from REST API
                val weather: WeatherResponse = response.body()!!
                val temperature = weather.main!!.temp
                weatherTemp.setText(temperature.toString() + " C")
                val windSpeed = weather.wind!!.speed
                weatherWind.setText(windSpeed.toString() + " m/s")
                val humidity = weather.main!!.humidity
                weatherHumidity.setText(humidity.toString() + " %")

                //Saves data in Firebase database
                val history = hashMapOf<String, Any>(
                    "city" to city,
                    "temperature" to temperature,
                    "wind" to windSpeed,
                    "humidity" to humidity
                )

                db.collection("weatherHistory").add(history)
            }

            //Incase of error/failure
            override fun onFailure(call: Call<WeatherResponse>, throwable: Throwable) {
            }
        })
    }
}