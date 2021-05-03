package com.wit.weather.Network;

import com.wit.weather.Constants;
import com.wit.weather.Models.WeatherModels;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Weather {
    @GET("onecall")
    Call<WeatherModels> getWeather(@QueryMap Map<String, String> options);
}
