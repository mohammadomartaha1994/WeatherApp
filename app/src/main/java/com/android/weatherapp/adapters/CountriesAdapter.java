package com.android.weatherapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.weatherapp.R;
import com.android.weatherapp.model.Parent;

import java.util.ArrayList;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {
    private List<Parent> countriesList = new ArrayList<>();
    private OnItemClickListener mListener;

    @NonNull
    @Override
    public CountriesAdapter.CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountriesAdapter.CountriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return position + 2;
    }

    @Override
    public void onBindViewHolder(@NonNull final CountriesAdapter.CountriesViewHolder holder, final int position) {
        holder.country_name.setText(countriesList.get(position).getTitle());
    }


    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public void setList(List<Parent> uList) {
        this.countriesList = uList;
        notifyDataSetChanged();
    }

    public class CountriesViewHolder extends RecyclerView.ViewHolder {
        TextView country_name;
        CardView item;
        public CountriesViewHolder(@NonNull View itemView) {
            super(itemView);
            country_name = itemView.findViewById(R.id.country_name);
            item = itemView.findViewById(R.id.country_item);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    if (mListener != null && index != RecyclerView.NO_POSITION){
                        mListener.OnItemClick(countriesList.get(index));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{void OnItemClick(Parent parent);}
    public void OnItemClickListener(OnItemClickListener listerner){mListener=listerner;}


}
