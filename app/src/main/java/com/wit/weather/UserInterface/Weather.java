package com.wit.weather.UserInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
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
    @BindView(R.id.imageViewRepoToolBar)
    ImageView mImageViewRepoToolBar;

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
        mTextViewTown.setText(cityName);

        mImageViewRepoToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}