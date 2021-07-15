package com.android.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.weatherapp.EndPoints;
import com.android.weatherapp.R;
import com.android.weatherapp.adapters.CountriesAdapter;
import com.android.weatherapp.adapters.WeatherAdapter;
import com.android.weatherapp.model.ConsolidatedWeather;
import com.android.weatherapp.view_model.CountriesViewModel;
import com.android.weatherapp.view_model.WeatherViewModel;

import java.util.List;

public class WeatherInfo extends AppCompatActivity {
    ImageView statusImage,backToHome;
    TextView name;
    WeatherViewModel weatherViewModel;
    RecyclerView currentWeatherList;
    WeatherAdapter weatherAdapter;
    ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        Intent fromCountries = getIntent();
        String id = fromCountries.getStringExtra("id");
        String cName = fromCountries.getStringExtra("cName");
        name = findViewById(R.id.cName);
        name.setText(cName);
        weatherViewModel = new ViewModelProvider(WeatherInfo.this).get(WeatherViewModel.class);
        weatherViewModel.getWeather(id);
        statusImage = findViewById(R.id.statusImage);
        backToHome = findViewById(R.id.backToHome);
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WeatherInfo.this,MainActivity.class);
                startActivity(i);
            }
        });
        currentWeatherList =findViewById(R.id.currentWeatherList);
        loading = findViewById(R.id.progressBarInfo);
        weatherAdapter = new WeatherAdapter();
        currentWeatherList.setLayoutManager(new LinearLayoutManager(this));
        currentWeatherList.setAdapter(weatherAdapter);
        weatherViewModel.weatherMutableLiveData.observe(this, new Observer<List<ConsolidatedWeather>>() {
            @Override
            public void onChanged(List<ConsolidatedWeather> weathers) {
                loading.setVisibility(View.GONE);
                weatherAdapter.setList(weathers);
            }
        });
    }
}