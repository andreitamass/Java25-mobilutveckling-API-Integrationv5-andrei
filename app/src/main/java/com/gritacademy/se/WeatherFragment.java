package com.gritacademy.se;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.firestore.FirebaseFirestore;


public class WeatherFragment extends Fragment {

    private final String API_KEY = "76ed108824c68079af4e65ae7ed839e3";

    public WeatherFragment() {
        super(R.layout.fragment_weather);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView weatherHumidity = view.findViewById(R.id.weatherHumidity);
        TextView weatherWind = view.findViewById(R.id.weatherWind);
        TextView weatherTemp = view.findViewById(R.id.weatherTemp);
        EditText searchCity = view.findViewById(R.id.searchCity);
        Button searchCityButton = view.findViewById(R.id.searchCityButton);

        searchCityButton.setOnClickListener(v -> {
            String city = searchCity.getText().toString();

            getWeather(city, weatherTemp, weatherWind, weatherHumidity);

        });
    }

    private void getWeather(String city, TextView weatherTemp, TextView weatherWind, TextView weatherHumidity) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);

        Call<WeatherResponse> call = weatherAPI.getWeather(
                city,
                API_KEY,
                "metric"
        );

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse weather = response.body();
                double temperature = weather.main.temp;
                weatherTemp.setText(temperature + " C");
                double windSpeed = weather.wind.speed;
                weatherWind.setText(windSpeed + " m/s");
                int humidity = weather.main.humidity;
                weatherHumidity.setText(humidity + " %");

                Map<String, Object> history = new HashMap<>();
                history.put("city", city);
                history.put("temperature", temperature);
                history.put("wind", windSpeed);
                history.put("humidity", humidity);

                db.collection("weatherHistory").add(history);

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable throwable) {
            }
        });
    }
}