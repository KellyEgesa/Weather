package com.wit.weather.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;

import com.wit.weather.Models.WeatherModels;
import com.wit.weather.R;

import org.parceler.Parcels;

public class Weather extends AppCompatActivity {
    WeatherModels weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weather = Parcels.unwrap(getIntent().getParcelableExtra("Weather"));
    }
}