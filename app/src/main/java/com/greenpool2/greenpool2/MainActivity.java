package com.greenpool2.greenpool2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.greenpool2.greenpool2.model.User;
import com.greenpool2.greenpool2.model.UserLoginRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class MainActivity extends BaseActivity  {
    ProgressDialog progressDialog;
    EditText memail, mpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memail =  findViewById(R.id.email);
        mpassword = findViewById(R.id.password);

        Button mlogin = (Button) findViewById(R.id.login);
        Button mregister = (Button) findViewById(R.id.register);

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 LoginTask login = new LoginTask();
                (new LoginTask()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            }
        });

        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                Intent registerActivity = new Intent(getApplicationContext(),MapActivity.class);
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

    public class  LoginTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params) {

            try
            {
            //for device below URL
            //String url = "http://localhost:8080/greenpool/v1/login";
            //for emulator below URL
            String url ="http://10.0.2.2:8080/greenpool/v1/login";

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            UserLoginRequest userLoginRequest = new UserLoginRequest();
            userLoginRequest.setPassword(MainActivity.this.mpassword.getText().toString());
            userLoginRequest.setUsername(MainActivity.this.memail.getText().toString());
            HttpEntity<UserLoginRequest> requestEntity = new HttpEntity<UserLoginRequest>(userLoginRequest,requestHeaders);
            RestTemplate restTemplate = new RestTemplate();

            //ResponseEntity<String> uri = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            User userResponse  = restTemplate.postForObject(url, requestEntity, User.class);
            if(userResponse != null && userResponse.getEmail().toString() != null) {
                Intent signinActivity = new Intent(getApplicationContext(), RideHomeActivity.class);
                startActivity(signinActivity);
                return null;
            }
            else {
                TextView errorText = findViewById(R.id.errorText);
                errorText.setText(userResponse.toString());
                return  null;
            }

        }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}