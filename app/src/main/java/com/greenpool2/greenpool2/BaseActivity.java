package com.greenpool2.greenpool2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
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
                finish();
                return true;

            case R.id.menu_registervehicle :
                Intent intentVehicle = new Intent(getApplicationContext(),VehicleRegistration.class);
                startActivity(intentVehicle);
                finish();
                return true;

            case R.id.menu_signout :
                Intent signout = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(signout);
                finish();
                return true;
            default:return super.onOptionsItemSelected(item);
        }

    }
}
