package com.android.weatherapp.view_model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.weatherapp.calls.WeatherCall;
import com.android.weatherapp.model.ConsolidatedWeather;
import com.android.weatherapp.model.WeatherInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {
    public MutableLiveData<List<ConsolidatedWeather>> weatherMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> failure = new MutableLiveData<>();

    public void getWeather(String woeid) {
        WeatherCall.getINSTANCE().getWeather(woeid).enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                weatherMutableLiveData.setValue(response.body().getConsolidatedWeather());
            }
            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                failure.setValue("Error");
            }
        });
    }
}