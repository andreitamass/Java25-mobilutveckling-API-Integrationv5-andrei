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

    }

}