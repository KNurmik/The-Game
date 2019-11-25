package com.example.phase1activity.UI.MenuScreens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivity;
import com.example.phase1activity.UI.UserAccess.LogInActivity;
import com.example.phase1activity.UI.UserAccess.RegisterActivity;

/**
 * MainActivity
 */
public class MainActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes the login button
        Button logInBtn = findViewById(R.id.logInButton);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens LogInActivity
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
            }
        });

        //Initializes the register button
        Button regBtn = findViewById(R.id.createAccountButton);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opens RegisterActivity
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }
}
