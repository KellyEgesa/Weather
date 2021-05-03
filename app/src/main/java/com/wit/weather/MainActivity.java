package com.wit.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.permissionButton)
    Button mPermissionButton;
    LocationManager locationManager;
    Boolean isPermissionGranted, isGPSEnabled;
    Coordinates currentLocation;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
            getUserLocation(locationManager);
        }
    }

    public void getUserLocation(LocationManager locationManager) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
             isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            Criteria criteria = new Criteria();
            if (!isGPSEnabled) {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            } else {
                criteria.setAccuracy(Criteria.ACCURACY_FINE);
            }
            locationManager.requestSingleUpdate(criteria, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    currentLocation = new Coordinates(location.getLatitude(), location.getLongitude());
                    Toast.makeText(MainActivity.this, location.toString(), Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPermissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        getUserLocation(locationManager);
    }


    public static class Coordinates {
        public float longitude;
        public float latitude;

        public Coordinates(float latitude, float longitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public Coordinates(double latitude, double longitude) {
            this.longitude = (float) longitude;
            this.latitude = (float) latitude;
        }
    }
}
// startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));