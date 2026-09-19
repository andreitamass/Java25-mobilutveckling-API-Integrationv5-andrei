package com.gritacademy.se;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    //REST API with GET method to get information from OpenWeather
    @GET("data/2.5/weather")
    Call<WeatherResponse> getWeather(
            @Query("q") String city,
            @Query("appid") String apiKey,
            @Query("units") String units
    );
}
