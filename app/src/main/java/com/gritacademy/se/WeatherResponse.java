package com.gritacademy.se;

public class WeatherResponse {
    public TemperatureData main;
    public WeatherWind wind;
}
class TemperatureData {
    public double temp;
}

class WeatherWind {
    public double speed;
}