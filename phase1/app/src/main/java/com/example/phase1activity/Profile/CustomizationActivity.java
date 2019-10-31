package com.example.phase1activity.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.phase1activity.R;
import com.example.phase1activity.presentation.MainMenu.StartActivity;

public class CustomizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);
        RadioButton green = findViewById(R.id.Green);
        RadioButton red = findViewById(R.id.Red);
        RadioButton blue = findViewById(R.id.Blue);

        red.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //CHANGE PROFILE COLOUR TO RED HERE
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //CHANGE PROFILE COLOUR TO BLUE HERE
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //CHANGE PROFILE COLOUR TO GREEN HERE
            }
        });
    }

    /**
     * Called when the user taps the Set button
     */
    public void setNickname(View view) {
        // Do something in response to button
    }
}
