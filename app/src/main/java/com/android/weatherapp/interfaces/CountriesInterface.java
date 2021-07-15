package com.android.weatherapp.interfaces;

import com.android.weatherapp.EndPoints;
import com.android.weatherapp.model.Parent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CountriesInterface {
    @GET(EndPoints.GET_COUNTRIES_LIST)
    public Call<List<Parent>> getCountries(@Query("query") String title);
}
