package com.wit.weather.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wit.weather.Models.WeatherModels;
import com.wit.weather.R;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    @BindView(R.id.textViewHumidity)
    TextView mTextViewHumidity;
    @BindView(R.id.textViewPressure)
    TextView mTextViewPressure;
    @BindView(R.id.textViewWind)
    TextView mTextViewWind;
    @BindView(R.id.textViewDate)
    TextView mTextViewDate;
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

        String temp = weather.getCurrent().getTemp() + "°";
        String feels = "Feels like " + weather.getCurrent().getFeelsLike() + "°";
        String photoUrl = "https://openweathermap.org/img/wn/" + weather.getCurrent().getWeather().get(0).getIcon() + "@4x.png";
        String humidity = weather.getCurrent().getHumidity() + "%";
        String pressure = weather.getCurrent().getPressure() + " hPa";
        String wind = weather.getCurrent().getWindSpeed() + " m/s";


        mDegreesTextView.setText(temp);

        Picasso.get().load(photoUrl).into(mWeatherImageView);
        mWeatherText.setText(weather.getCurrent().getWeather().get(0).getMain().toUpperCase());
        mWeatherDescriptiontextView.setText(weather.getCurrent().getWeather().get(0).getDescription());
        mFeelsTextView.setText(feels);
        mTextViewHumidity.setText(humidity);
        mTextViewPressure.setText(pressure);
        mTextViewWind.setText(wind);

        Date date = new Date(weather.getCurrent().getDt() * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEEEEE, dd MMM yyyy");
        String dateTrans = simpleDateFormat.format(date);
        mTextViewDate.setText(dateTrans);
        mTextViewTown.setText(cityName);

    }
}