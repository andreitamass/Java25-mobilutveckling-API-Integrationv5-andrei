package com.gritacademy.se;

//Converts JSON-file to Java object
public class WeatherResponse {
    public TemperatureData main;
    public WeatherWind wind;
}
class TemperatureData {
    public double temp;
    public int humidity;
}

class WeatherWind {
    public double speed;
}
