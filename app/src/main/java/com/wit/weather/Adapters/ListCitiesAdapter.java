package com.wit.weather.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.$Gson$Preconditions;
import com.wit.weather.Constants;
import com.wit.weather.Models.Cities;
import com.wit.weather.Models.WeatherModels;
import com.wit.weather.Network.Weather;
import com.wit.weather.Network.WeatherClient;
import com.wit.weather.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        @BindView(R.id.cardCity)
        CardView mcardCity;
        Context mContext;

        public ListCitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

        }

        public void bindCities(Cities city) {
            String cityName = city.getCityName()+", "+city.getCountryName();
            mCityName.setText(cityName);

            mcardCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Weather client = WeatherClient.urlRequest();
                    Map<String, String> queryParams = new HashMap<>();
                    queryParams.put("lat", String.valueOf( city.getLatitude()));
                    queryParams.put("lon", String.valueOf( city.getLongitude()));
                    queryParams.put("lat", String.valueOf( city.getLatitude()));
                    queryParams.put("exclude", "minutely,hourly");
                    queryParams.put("appid", Constants.ACCESS_TOKEN);


                    Call<WeatherModels> call = client.getWeather(queryParams);

                   call.enqueue(new Callback<WeatherModels>() {
                       @Override
                       public void onResponse(Call<WeatherModels> call, Response<WeatherModels> response) {
                           if(response.isSuccessful()){
                               Intent intent = new Intent(mContext, com.wit.weather.UserInterface.Weather.class);
                               intent.putExtra("Weather", Parcels.wrap(response.body()));
                               mContext.startActivity(intent);
                           }
                       }

                       @Override
                       public void onFailure(Call<WeatherModels> call, Throwable t) {

                       }
                   });
                }
            });
        }
    }
}
