package com.greenpool2.greenpool2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText memail =  findViewById(R.id.email);
        EditText mpassword = findViewById(R.id.password);

        Button mlogin = (Button) findViewById(R.id.login);
        Button mregister = (Button) findViewById(R.id.register);

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="http://localhost:8080/greenpool/v1/login";
                RestTemplate restTemplate = new RestTemplate();

                // Add the String message converter
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

                // Make the HTTP GET request, marshaling the response to a String
                User result = restTemplate.getForObject(url, User.class);
            }
        });

        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                Intent registerActivity = new Intent(getApplicationContext(),UserRegistrationActivity.class);
                startActivity(registerActivity);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_editprofile :
                Intent intentProfile = new Intent(getApplicationContext(),UserRegistrationActivity.class);
                startActivity(intentProfile);
                return true;

            case R.id.menu_registervehicle :
                Intent intentVehicle = new Intent(getApplicationContext(),VehicleRegistration.class);
                startActivity(intentVehicle);
                return true;

            case R.id.menu_signout :
                Intent signout = new Intent(getApplicationContext(),UserRegistrationActivity.class);
                startActivity(signout);
                return true;
            default:return super.onOptionsItemSelected(item);
        }

    }

}