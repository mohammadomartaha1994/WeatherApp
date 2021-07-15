package com.android.weatherapp.view_model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.weatherapp.calls.CountriesCall;
import com.android.weatherapp.model.Parent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountriesViewModel extends ViewModel {
    public MutableLiveData<List<Parent>> countriesMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> failure = new MutableLiveData<>();
    public void getCountries(String title) {
        CountriesCall.getINSTANCE().getCountries(title).enqueue(new Callback<List<Parent>>() {
            @Override
            public void onResponse(Call<List<Parent>> call, Response<List<Parent>> response) {
                countriesMutableLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<Parent>> call, Throwable t) {
                failure.setValue("Error");
            }
        });
    }
}
