package com.android.weatherapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.weatherapp.EndPoints;
import com.android.weatherapp.R;
import com.android.weatherapp.model.ConsolidatedWeather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private List<ConsolidatedWeather> weatherList = new ArrayList<>();
    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("testtsetse",viewType+"");
        if (viewType == 2)
            return new WeatherViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.first_weather_item, parent, false));
        else
        return new WeatherViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return position + 2;
    }

    @Override
    public void onBindViewHolder(@NonNull final WeatherViewHolder holder, final int position) {
        holder.current.setText(Math.round(weatherList.get(position).getTheTemp())+"°C");
        holder.min.setText(Math.round(weatherList.get(position).getMinTemp())+"°C");
        holder.max.setText(Math.round(weatherList.get(position).getMaxTemp())+"°C");
        holder.date.setText(weatherList.get(position).getApplicableDate());
        holder.weather_state_name.setText(weatherList.get(position).getWeatherStateName());

        Picasso.get().load(EndPoints.URL_BASE+EndPoints.IMAGE_WEATHER+weatherList.get(position).getWeatherStateAbbr()+".png").into(holder.statusImage);
    }


    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void setList(List<ConsolidatedWeather> uList) {
        this.weatherList = uList;
        notifyDataSetChanged();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView current, max,min,weather_state_name,date;
        ImageView statusImage;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            current = itemView.findViewById(R.id.current);
            max = itemView.findViewById(R.id.max);
            min = itemView.findViewById(R.id.min);
            weather_state_name = itemView.findViewById(R.id.weather_state_name);
            date = itemView.findViewById(R.id.date);
            statusImage = itemView.findViewById(R.id.statusImage);
        }
    }
}
