package com.greenpool2.greenpool2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class JoinRideActivity extends AppCompatActivity {
    Button cancel,findRide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_ride);

        cancel = findViewById(R.id.cancel);
        findRide = findViewById(R.id.findRide);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent joinRideActivity = new Intent(getApplicationContext(),RideHomeActivity.class);
                startActivity(joinRideActivity);
            }
        });

        findRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(JoinRideActivity.this, "Your request has been taken, will confirm shortly ", Toast.LENGTH_SHORT).show();
                Intent joinRideActivity = new Intent(getApplicationContext(),RideHomeActivity.class);
                startActivity(joinRideActivity);
            }
        });
    }
}
