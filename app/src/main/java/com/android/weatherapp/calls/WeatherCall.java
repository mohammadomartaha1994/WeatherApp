package com.android.weatherapp.calls;

import com.android.weatherapp.EndPoints;
import com.android.weatherapp.interfaces.WeatherInterface;
import com.android.weatherapp.model.WeatherInfo;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherCall {
    private WeatherInterface weatherInterface;
    private static WeatherCall INSTANCE;

    public WeatherCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherInterface = retrofit.create(WeatherInterface.class);
    }

    public static WeatherCall getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new WeatherCall();
        }
        return INSTANCE;
    }

    public Call<WeatherInfo> getWeather(String woeid) {
        return weatherInterface.getWeatherInfo(woeid);
    }

}
