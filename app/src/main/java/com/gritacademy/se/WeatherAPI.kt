package com.gritacademy.se

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    //REST API with GET method to get information from OpenWeather
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("q") city: String?,
        @Query("appid") apiKey: String?,
        @Query("units") units: String?
    ): Call<WeatherResponse>
}
