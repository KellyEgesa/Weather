package com.wit.weather.Network;

import com.wit.weather.Constants;
import com.wit.weather.Models.WeatherModels;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Weather {
@GET("?lat={latitude}&lon={longitude}&exclude=minutely,hourly&appid="+ Constants.ACCESS_TOKEN)
    Call<WeatherModels> getWeather(@Path("latitude") String latitude, @Path("longitude") String longitude);
}
