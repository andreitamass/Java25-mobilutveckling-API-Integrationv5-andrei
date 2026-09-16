package com.gritacademy.se;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MenuFragment extends Fragment {

    public MenuFragment() {
        super(R.layout.fragment_menu);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button weatherButton = view.findViewById(R.id.weatherButton);
        Button currencyButton = view.findViewById(R.id.currencyButton);

        weatherButton.setOnClickListener(v -> openWeather());

        currencyButton.setOnClickListener(v -> openCurrency());
    }

    private void openCurrency() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new CurrencyFragment())
                .addToBackStack(null)
                .commit();
    }

    private void openWeather() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new WeatherFragment())
                .addToBackStack(null)
                .commit();
    }
}