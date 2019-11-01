package com.example.phase1activity.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import com.example.phase1activity.AbstractActivities;
import com.example.phase1activity.Profile.AppManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.phase1activity.R;
import com.example.phase1activity.SaveAPI.AndroidSaver;
import com.example.phase1activity.SaveAPI.ISaver;
import com.example.phase1activity.presentation.MainMenu.StartActivity;

public class CustomizationActivity extends AbstractActivities {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);

        RadioButton green = findViewById(R.id.Green);
        RadioButton red = findViewById(R.id.Red);
        RadioButton blue = findViewById(R.id.Blue);
        RadioButton song1 = findViewById(R.id.Song1);
        RadioButton song2 = findViewById(R.id.Song2);
        Button set = findViewById(R.id.set);
        final EditText name = findViewById(R.id.nickname);

        final ISaver iSaver = new AndroidSaver(this.getApplicationContext());

        red.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Profile profile = app.getProfile();
                profile.setColour("Red");
                iSaver.saveData(profile.username + "," + profile.password + "," + profile.colour + "," + profile.nickname + "," + profile.song);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                app.getProfile().setColour("Blue");
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                app.getProfile().setColour("Green");
            }
        });

        song1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                app.getProfile().setSong(1);
            }
        });

        song2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                app.getProfile().setSong(2);
            }
        });


        set.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nickname = name.getText().toString();
                app.getProfile().setNickname(nickname);
                System.out.println(nickname);
            }
        });
    }
}
