package com.android.weatherapp.interfaces;

import com.android.weatherapp.EndPoints;
import com.android.weatherapp.model.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherInterface {
    @GET(EndPoints.CURRENT_WEATHER)
    public Call<WeatherInfo> getWeatherInfo(@Path(value = "woeid", encoded = true) String woeid);

}
