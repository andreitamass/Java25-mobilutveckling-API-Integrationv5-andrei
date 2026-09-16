package com.gritacademy.se;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WeatherFragment extends Fragment {

    private final String API_KEY = "76ed108824c68079af4e65ae7ed839e3";

    public WeatherFragment() {
        super(R.layout.fragment_weather);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText searchCity = view.findViewById(R.id.searchCity);
        Button searchCityButton = view.findViewById(R.id.searchCityButton);

        searchCityButton.setOnClickListener(v -> {
            String city = searchCity.getText().toString();

            getWeather(city);

        });
    }

    private void getWeather(String city) {

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
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable throwable) {
            }
        });
    }
}