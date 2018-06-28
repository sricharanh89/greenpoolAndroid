package com.greenpool2.greenpool2;

import android.Manifest;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends BaseActivity {

    private GoogleMap mMap;
    private Button btnFindPath;
    private EditText etOrigin;
    private EditText etDestination;
    private Double srcLat, srclong,destlat,destlong;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        btnFindPath = (Button) findViewById(R.id.shareRide);
        etOrigin = (EditText) findViewById(R.id.etOrigin);
        etDestination = (EditText) findViewById(R.id.etDestination);

        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             (new GeocodingFromAddressTask(etOrigin.getText().toString())).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                Toast.makeText(MapActivity.this, "Your Ride has been Scheduled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class GeocodingFromAddressTask extends AsyncTask<Void, Void, LatLng> {

        private final String location;

        public GeocodingFromAddressTask(String location) {
            this.location = location;
        }

        @Override
        protected LatLng doInBackground(Void... params) {
            Geocoder gc = new Geocoder(getApplicationContext());
            LatLng latLng = null;
            try {
                List<Address> srcAddress = gc.getFromLocationName(etOrigin.getText().toString(), 1);

                for (Address a : srcAddress) {
                    if (a.hasLatitude() && a.hasLongitude()) {
                        Double lat = a.getLatitude();
                        Double longitude = a.getLongitude();
                        srcLat = lat;
                        srclong =longitude;
                        latLng =  new LatLng(lat, longitude);
                    }
                }
                List<Address> destAddress = gc.getFromLocationName(etDestination.getText().toString(), 1);

                for (Address a : destAddress) {
                    if (a.hasLatitude() && a.hasLongitude()) {
                        Double lat = a.getLatitude();
                        Double longitude = a.getLongitude();
                        destlat = lat;
                        destlong = longitude;
                        latLng =  new LatLng(lat, longitude);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return latLng;
        }

        @Override
        protected void onPostExecute(LatLng latLng) {
            super.onPostExecute(latLng);
        }
    }
}
