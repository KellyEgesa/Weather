package com.wit.weather.UserInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wit.weather.Adapters.ListWeatherAdapter;
import com.wit.weather.Models.Current;
import com.wit.weather.Models.WeatherModels;
import com.wit.weather.R;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Weather extends AppCompatActivity {
    @BindView(R.id.recyclerViewWeather)
    RecyclerView mRecyclerViewWeather;
    @BindView(R.id.textViewTown)
    TextView mTextViewTown;

    WeatherModels weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        weather = Parcels.unwrap(getIntent().getParcelableExtra("Weather"));
        String cityName = getIntent().getStringExtra("CityName");

        ArrayList weatherList = weather.getDaily();
        weatherList.add(0, weather.getCurrent());

        ListWeatherAdapter listWeatherAdapter = new ListWeatherAdapter(weatherList);
        mRecyclerViewWeather.setAdapter(listWeatherAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Weather.this);
        mRecyclerViewWeather.setLayoutManager(layoutManager);

//        String temp = weather.getCurrent().getTemp() + "°";
//        String feels = "Feels like " + weather.getCurrent().getFeelsLike() + "°";
//        String photoUrl = "https://openweathermap.org/img/wn/" + weather.getCurrent().getWeather().get(0).getIcon() + "@4x.png";
//        String humidity = weather.getCurrent().getHumidity() + "%";
//        String pressure = weather.getCurrent().getPressure() + " hPa";
//        String wind = weather.getCurrent().getWindSpeed() + " m/s";
//
//
//        mDegreesTextView.setText(temp);
//
//        Picasso.get().load(photoUrl).into(mWeatherImageView);
//        mWeatherText.setText(weather.getCurrent().getWeather().get(0).getMain().toUpperCase());
//        mWeatherDescriptiontextView.setText(weather.getCurrent().getWeather().get(0).getDescription());
//        mFeelsTextView.setText(feels);
//        mTextViewHumidity.setText(humidity);
//        mTextViewPressure.setText(pressure);
//        mTextViewWind.setText(wind);
//
//        Date date = new Date(weather.getCurrent().getDt() * 1000L);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEEEEE, dd MMM yyyy");
//        String dateTrans = simpleDateFormat.format(date);
//        mTextViewDate.setText(dateTrans);
        mTextViewTown.setText(cityName);

    }
}