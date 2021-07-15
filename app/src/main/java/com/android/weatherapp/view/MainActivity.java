package com.android.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.weatherapp.R;
import com.android.weatherapp.adapters.CountriesAdapter;
import com.android.weatherapp.adapters.WeatherAdapter;
import com.android.weatherapp.model.ConsolidatedWeather;
import com.android.weatherapp.model.Parent;
import com.android.weatherapp.view_model.CountriesViewModel;
import com.android.weatherapp.view_model.WeatherViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView statusImage,currentWeather;
    WeatherViewModel weatherViewModel;
    CountriesViewModel countriesViewModel;
    RecyclerView contryWeatherList,currentWeatherList;
    WeatherAdapter weatherAdapter;
    CountriesAdapter countriesAdapter;
    EditText editTextSearch;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusImage = findViewById(R.id.statusImage);
        currentWeatherList =findViewById(R.id.currentWeatherList);
        currentWeather = findViewById(R.id.currentWeather);
        contryWeatherList =findViewById(R.id.contryWeatherList);
        editTextSearch = (EditText)findViewById(R.id.editTextSearch);
        loading = findViewById(R.id.progressBar);
        weatherAdapter = new WeatherAdapter();
        countriesAdapter = new CountriesAdapter();
        currentWeatherList.setLayoutManager(new LinearLayoutManager(this));
        contryWeatherList.setLayoutManager(new LinearLayoutManager(this));
        currentWeatherList.setAdapter(weatherAdapter);
        contryWeatherList.setAdapter(countriesAdapter);
        weatherViewModel = new ViewModelProvider(MainActivity.this).get(WeatherViewModel.class);
        countriesViewModel = new ViewModelProvider(MainActivity.this).get(CountriesViewModel.class);
        weatherViewModel.getWeather("368148");
        weatherViewModel.weatherMutableLiveData.observe(this, new Observer<List<ConsolidatedWeather>>() {
            @Override
            public void onChanged(List<ConsolidatedWeather> weathers) {
                loading.setVisibility(View.GONE);
                weatherAdapter.setList(weathers);
            }
        });
        currentWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSearch.setText("");
                contryWeatherList.setVisibility(View.GONE);
                currentWeatherList.setVisibility(View.VISIBLE);
            }
        });
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                contryWeatherList.setVisibility(View.VISIBLE);
                currentWeatherList.setVisibility(View.GONE);
                countriesViewModel.getCountries(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        countriesViewModel.countriesMutableLiveData.observe(this, new Observer<List<Parent>>() {
            @Override
            public void onChanged(List<Parent> parents) {
                if (parents == null){
                    contryWeatherList.setVisibility(View.GONE);
                    currentWeatherList.setVisibility(View.VISIBLE);
                }
                else countriesAdapter.setList(parents);

                loading.setVisibility(View.GONE);
            }
        });
        countriesAdapter.OnItemClickListener(new CountriesAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Parent parent) {
                Intent goToInfo = new Intent(MainActivity.this,WeatherInfo.class);
                goToInfo.putExtra("id",parent.getWoeid().toString());
                goToInfo.putExtra("cName",parent.getTitle());
                startActivity(goToInfo);
            }
        });
    }
}