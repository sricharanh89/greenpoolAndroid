package com.greenpool2.greenpool2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.greenpool2.greenpool2.model.User;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class UserRegistrationActivity extends BaseActivity  {
    private EditText ufirstName, ulastName,uemailId,umobile,upassword,usId;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ufirstName =  findViewById(R.id.firstName);
        ulastName =  findViewById(R.id.lastName);
        uemailId = findViewById(R.id.emailId);
        umobile= findViewById(R.id.mobile);
        upassword = findViewById(R.id.password);
        usId = findViewById(R.id.sId);
        radioGroup = (RadioGroup) findViewById(R.id.radioSex);

        Button cancelBtn = findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(registerActivity);
            }
        });

        Button saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                (new RegistrationTask()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });

    }

    public class RegistrationTask extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... params) {

            try
            {
                //for device below URL
                //String url = "http://localhost:8080/greenpool/v1/login";
                //for emulator below URL
                String url ="http://10.0.2.2:8080/greenpool/v1/registeruser";

                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                User userRequest = new User();
                userRequest.setFirstname(UserRegistrationActivity.this.ufirstName.getText().toString());
                userRequest.setLastname(UserRegistrationActivity.this.ulastName.getText().toString());
                userRequest.setEmail(UserRegistrationActivity.this.uemailId.getText().toString());
                userRequest.setId(UserRegistrationActivity.this.usId.getText().toString());
                userRequest.setMobile(UserRegistrationActivity.this.umobile.getText().toString());
                userRequest.setGender(UserRegistrationActivity.this.radioButton.getText().toString());
                HttpEntity<User> requestEntity = new HttpEntity<User>(userRequest,requestHeaders);
                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
                //User userResponse  = restTemplate.postForObject(url, requestEntity, User.class);

                //String res = userResponse;//uri.getBody().toString();
                if(userResponse.getStatusCode().toString() == "200" && userResponse.getBody() == "CREATED") {
                    Intent registerActivity = new Intent(getApplicationContext(), RideHomeActivity.class);
                    startActivity(registerActivity);
                }
                else{

                }

                return userResponse.toString();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("data", s.toString());
        }
    }
}
