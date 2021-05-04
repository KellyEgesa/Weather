package com.wit.weather.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wit.weather.Models.Current;
import com.wit.weather.Models.Daily;
import com.wit.weather.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListWeatherAdapter extends RecyclerView.Adapter<ListWeatherAdapter.ListWeatherViewHolder> {
    private ArrayList weather;

    public ListWeatherAdapter(ArrayList weather) {
        this.weather = weather;
    }

    @NonNull
    @Override
    public ListWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather, parent, false);
        ListWeatherViewHolder viewHolder = new ListWeatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListWeatherViewHolder holder, int position) {
        if (weather.get(position) instanceof Current) {
            holder.bindWeather((Current) weather.get(position));
        } else {
            holder.bindWeather((Daily) weather.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return weather.size();
    }

    public class ListWeatherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.degreesTextView)
        TextView mDegreesTextView;
        @BindView(R.id.weatherImageView)
        ImageView mWeatherImageView;
        @BindView(R.id.weatherText)
        TextView mWeatherText;
        @BindView(R.id.weatherDescriptiontextView)
        TextView mWeatherDescriptiontextView;
        @BindView(R.id.feelsTextView)
        TextView mFeelsTextView;
        @BindView(R.id.textViewHumidity)
        TextView mTextViewHumidity;
        @BindView(R.id.textViewPressure)
        TextView mTextViewPressure;
        @BindView(R.id.textViewWind)
        TextView mTextViewWind;
        @BindView(R.id.textViewDate)
        TextView mTextViewDate;

        public ListWeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindWeather(Current weather) {

            String temp = weather.getTemp() + "°";
            String feels = "Feels like " + weather.getFeelsLike() + "°";
            String photoUrl = "https://openweathermap.org/img/wn/" + weather.getWeather().get(0).getIcon() + "@4x.png";
            String humidity = weather.getHumidity() + "%";
            String pressure = weather.getPressure() + " hPa";
            String wind = weather.getWindSpeed() + " m/s";
            String date = "Current Weather";

            mDegreesTextView.setText(temp);

            Picasso.get().load(photoUrl).into(mWeatherImageView);
            mWeatherText.setText(weather.getWeather().get(0).getMain().toUpperCase());
            mWeatherDescriptiontextView.setText(weather.getWeather().get(0).getDescription());
            mFeelsTextView.setText(feels);
            mTextViewHumidity.setText(humidity);
            mTextViewPressure.setText(pressure);
            mTextViewWind.setText(wind);

            mTextViewDate.setText(date);

        }

        public void bindWeather(Daily weather) {

            String temp = weather.getTemp().getMax() + "°";
            String feels = "Feels like " + weather.getFeelsLike().getEve() + "°";
            String photoUrl = "https://openweathermap.org/img/wn/" + weather.getWeather().get(0).getIcon() + "@4x.png";
            String humidity = weather.getHumidity() + "%";
            String pressure = weather.getPressure() + " hPa";
            String wind = weather.getWindSpeed() + " m/s";

            mDegreesTextView.setText(temp);

            Picasso.get().load(photoUrl).into(mWeatherImageView);
            mWeatherText.setText(weather.getWeather().get(0).getMain().toUpperCase());
            mWeatherDescriptiontextView.setText(weather.getWeather().get(0).getDescription());
            mFeelsTextView.setText(feels);
            mTextViewHumidity.setText(humidity);
            mTextViewPressure.setText(pressure);
            mTextViewWind.setText(wind);

            Date date = new Date(weather.getDt() * 1000L);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEEEEE, dd MMM yyyy");
            String dateTrans = simpleDateFormat.format(date);
            mTextViewDate.setText(dateTrans);

        }
    }
}
