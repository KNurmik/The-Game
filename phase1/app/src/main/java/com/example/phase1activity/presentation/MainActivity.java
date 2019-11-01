package com.example.phase1activity.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.AbstractActivities;
import com.example.phase1activity.R;
import com.example.phase1activity.presentation.LogIn.LogInActivity;
import com.example.phase1activity.presentation.Register.RegisterActivity;

public class MainActivity extends AbstractActivities {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logInBtn = findViewById(R.id.logInButton);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
            }
        });

        Button regBtn = findViewById(R.id.createAccountButton);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }
}
