package com.wit.weather.Models;

public class Cities {
    private double latitude;
    private double longitude;
    private String cityName;

    public Cities(double latitude, double longitude, String cityName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityName = cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCityName() {
        return cityName;
    }
}
