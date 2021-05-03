package com.wit.weather.Network;

import com.wit.weather.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {
    public static Retrofit retrofit = null;

    public static Weather urlRequest() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Constants.WEATHER_ENPOINT).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(Weather.class);
    }
}
