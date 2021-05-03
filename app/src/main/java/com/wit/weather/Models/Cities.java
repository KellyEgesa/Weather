package com.wit.weather.Models;

public class Cities {
    private double latitude;
    private double longitude;
    private String cityName;
    private String countryName;

    public Cities(double latitude, double longitude, String cityName, String countryName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCityName() {
        return cityName;
    }
}
