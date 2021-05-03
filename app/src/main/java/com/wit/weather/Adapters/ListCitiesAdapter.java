package com.wit.weather.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wit.weather.Models.Cities;
import com.wit.weather.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListCitiesAdapter extends RecyclerView.Adapter<ListCitiesAdapter.ListCitiesViewHolder> {
    private ArrayList<Cities> cities;

    public ListCitiesAdapter(ArrayList<Cities> cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public ListCitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities, parent, false);
        ListCitiesViewHolder viewHolder = new ListCitiesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListCitiesViewHolder holder, int position) {
        holder.bindCities(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class ListCitiesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cityTextView)
        TextView mCityName;

        public ListCitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindCities(Cities cities) {
            mCityName.setText(cities.getCityName());
        }
    }
}
