package com.example.phase1activity.Profile;

import com.example.phase1activity.AbstractActivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.phase1activity.R;
import com.example.phase1activity.SaveAPI.AndroidSaver;
import com.example.phase1activity.SaveAPI.ISaver;
import com.example.phase1activity.presentation.MainMenu.StartActivity;

public class CustomizationActivity extends AbstractActivities {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);

        RadioButton red = findViewById(R.id.Red);
        RadioButton green = findViewById(R.id.Green);
        RadioButton blue = findViewById(R.id.Blue);
        RadioButton song1 = findViewById(R.id.Song1);
        RadioButton song2 = findViewById(R.id.Song2);
        Button set = findViewById(R.id.set);
        final EditText name = findViewById(R.id.nickname);
        Button back = findViewById(R.id.menu);

        final Profile profile = app.getProfile();

        /**
         * The two radio groups used to select a colour and song
         */
        RadioGroup colours = findViewById(R.id.radioGroup1);
        RadioGroup songs = findViewById(R.id.radioGroup2);

        //Whichever color the user selects for their profile should be the default option when
        //opening the CustomizationActivity
        if (profile.getColour() == Color.RED){
            colours.check(R.id.Red);
        }
        else if (profile.getColour() == Color.BLUE){
            colours.check(R.id.Blue);
        }
        else{
            colours.check(R.id.Green);
        }

        //Whichever song the user selects for their profile should be the default option when
        //opening the CustomizationActivity
        if (profile.getSong() == 0){
            songs.check(R.id.Song1);
        }
        else{
            songs.check(R.id.Song2);
        }

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CustomizationActivity.this, StartActivity.class));

            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Sets the colour to red
                profile.setColour("red");
                saveProfileData(profile);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Sets the colour to blue
                profile.setColour("blue");
                saveProfileData(profile);
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Sets the colour to green
                profile.setColour("green");
                saveProfileData(profile);
            }
        });

        song1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Sets the song to song0
                profile.setSong(0);
                app.setSongNumber(0);
                app.changeMusic();
                saveProfileData(profile);
            }
        });

        song2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Sets the song to song1
                profile.setSong(1);
                app.setSongNumber(1);
                app.changeMusic();
                saveProfileData(profile);
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Sets the user's nickname to whatever they typed into the Text Box
                profile.setNickname(name.getText().toString());
                saveProfileData(profile);
            }
        });
    }

    void saveProfileData(Profile profile) {
        //Saves the user's customization options to a local file.
        ISaver iSaver = new AndroidSaver(this.getApplicationContext());
        iSaver.saveData(profile.username + "," + profile.password + "," + profile.nickname + "," + profile.colour + "," + profile.song + "," + profile.gameLevel + "," + profile.totalScoreStat + "," + profile.fastestRxnStat + "," + profile.totalMovesStat);
    }
}
