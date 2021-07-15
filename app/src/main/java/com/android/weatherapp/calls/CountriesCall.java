package com.android.weatherapp.calls;

import com.android.weatherapp.EndPoints;
import com.android.weatherapp.interfaces.CountriesInterface;
import com.android.weatherapp.model.Parent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesCall {
    private CountriesInterface countriesInterface;
    private static CountriesCall INSTANCE;

    public CountriesCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        countriesInterface = retrofit.create(CountriesInterface.class);
    }

    public static CountriesCall getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new CountriesCall();
        }
        return INSTANCE;
    }

    public Call<List<Parent>> getCountries(String title) {
        return countriesInterface.getCountries(title);
    }


}
