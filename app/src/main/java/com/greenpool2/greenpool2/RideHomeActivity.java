package com.greenpool2.greenpool2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RideHomeActivity extends BaseActivity {
    Button shareRide,joinRide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_home);

        shareRide = findViewById(R.id.shareRide);
        joinRide = findViewById(R.id.joinRide);
        shareRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareRideActivity = new Intent(getApplicationContext(),MapActivity.class);
                startActivity(shareRideActivity);
            }
        });
        joinRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent joinRideActivity = new Intent(getApplicationContext(),JoinRideActivity.class);
                startActivity(joinRideActivity);
            }
        });
    }
}
