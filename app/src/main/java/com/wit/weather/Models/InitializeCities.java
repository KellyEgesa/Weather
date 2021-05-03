package com.wit.weather.Models;

import java.util.ArrayList;

public class InitializeCities {
    private ArrayList<Cities> citiesArrayList = new ArrayList<>();

    public InitializeCities() {
        Cities lisbon = new Cities(38.7223, 9.1393, "Lisbon", "Portugal");
        citiesArrayList.add(lisbon);
        Cities madrid = new Cities(40.4168, 3.7038, "Madrid", "Spain");
        citiesArrayList.add(madrid);
        Cities paris = new Cities(48.8566, 2.3522, "Paris", "France");
        citiesArrayList.add(paris);
        Cities berlin = new Cities(52.5200, 13.4050, "Berlin", "Germany");
        citiesArrayList.add(berlin);
        Cities copenhagen = new Cities(55.6761, 12.5683, "Copenhagen", "Denmark");
        citiesArrayList.add(copenhagen);
        Cities rome = new Cities(41.9028, 12.4964, "Rome", "Italy");
        citiesArrayList.add(rome);
        Cities london = new Cities(51.5074, 0.1278, "London", "England");
        citiesArrayList.add(london);
        Cities dublin = new Cities(53.3498, 6.2603, "Dublin", "Republic of Ireland");
        citiesArrayList.add(dublin);
        Cities prague = new Cities(50.0755, 14.4378, "Prague", "Czech");
        citiesArrayList.add(prague);
        Cities vienna = new Cities(48.2082, 16.3738, "Vienna", "Austria");
        citiesArrayList.add(vienna);
    }

    public ArrayList<Cities> getCitiesArrayList() {
        return citiesArrayList;
    }
}
