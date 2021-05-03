package com.wit.weather.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wit.weather.Models.WeatherModels;
import com.wit.weather.R;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Weather extends AppCompatActivity {
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

    WeatherModels weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        weather = Parcels.unwrap(getIntent().getParcelableExtra("Weather"));
        String temp = weather.getCurrent().getTemp() + "°";
        String feels = "Feels like " + weather.getCurrent().getFeelsLike() + "°";
        mDegreesTextView.setText(temp);
        String photoUrl = "https://openweathermap.org/img/wn/" + weather.getCurrent().getWeather().get(0).getIcon() + "@4x.png";
        Picasso.get().load(photoUrl).into(mWeatherImageView);
        Log.i("Photo", "http://openweathermap.org/img/wn/" + weather.getCurrent().getWeather().get(0).getIcon() + "@4x.png");
        mWeatherText.setText(weather.getCurrent().getWeather().get(0).getMain().toUpperCase());
        mWeatherDescriptiontextView.setText(weather.getCurrent().getWeather().get(0).getDescription());
        mFeelsTextView.setText(feels);


    }
}