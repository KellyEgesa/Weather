package com.wit.weather.UserInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.wit.weather.Adapters.ListCitiesAdapter;
import com.wit.weather.Models.Cities;
import com.wit.weather.Models.InitializeCities;
import com.wit.weather.R;
import com.wit.weather.UserInterface.Fragments.LocationPermissionFragment;
import com.wit.weather.UserInterface.Fragments.SwitchOnGpsFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.cityRecyclerView)
    RecyclerView mCityRecyclerView;

    ListCitiesAdapter listCitiesAdapter;
    LocationManager locationManager;

    LocationPermissionFragment locationPermissionFragment;

    Boolean isGPSEnabled = false;
    ArrayList<Cities> cities = new InitializeCities().getCitiesArrayList();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            locationPermissionFragment.dismiss();
            getUserLocation(locationManager);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        getUserLocation(locationManager);

        listCitiesAdapter = new ListCitiesAdapter(cities);
        mCityRecyclerView.setAdapter(listCitiesAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mCityRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserLocation(locationManager);
    }

    public void getUserLocation(LocationManager locationManager) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            FragmentManager fm = getSupportFragmentManager();
            locationPermissionFragment = new LocationPermissionFragment();
            locationPermissionFragment.show(fm, "Permission");
        } else {
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            Criteria criteria = new Criteria();
            if (!isGPSEnabled) {
                FragmentManager fm = getSupportFragmentManager();
                SwitchOnGpsFragment switchOnGpsFragment = new SwitchOnGpsFragment();
                switchOnGpsFragment.show(fm, "GPS");
            } else {
                criteria.setAccuracy(Criteria.ACCURACY_FINE);
                locationManager.requestSingleUpdate(criteria, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                            String cityName = addresses.get(0).getLocality();

                            if (cityName == null) {
                                cityName = addresses.get(0).getAdminArea();
                            }

                            String countryName = addresses.get(0).getCountryName();
                            Cities userCity = new Cities(location.getLatitude(), location.getLongitude(), cityName, countryName);
                            cities.add(0, userCity);
                            listCitiesAdapter.notifyDataSetChanged();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                    }
                }, null);
            }
        }
    }
}