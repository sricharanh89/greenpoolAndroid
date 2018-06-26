package com.greenpool2.greenpool2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegistrationActivity extends AppCompatActivity  {
    private EditText ufirstName, ulastName,uemailId,umobile,upassword;

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

        Button cancelBtn = findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(registerActivity);
            }
        });

    }
}
