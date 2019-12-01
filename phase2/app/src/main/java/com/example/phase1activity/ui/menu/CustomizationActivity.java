package com.example.phase1activity.ui.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.phase1activity.service.AppManager;
import com.example.phase1activity.R;
import com.example.phase1activity.ui.abstraction.AbstractActivity;

/**
 * Customization Activity
 */
public class CustomizationActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);

        // Initialize buttons and text fields.
        RadioButton red = findViewById(R.id.Red);
        RadioButton green = findViewById(R.id.Green);
        RadioButton blue = findViewById(R.id.Blue);
        RadioButton song1 = findViewById(R.id.Song1);
        RadioButton song2 = findViewById(R.id.Song2);
        RadioButton nosong = findViewById(R.id.nosong);

        Button setNicknameButton = findViewById(R.id.set);
        Button backButton = findViewById(R.id.menu);

        final EditText nicknameEntry = findViewById(R.id.nickname);
        final TextView nicknameErrorMessage = findViewById(R.id.errorMessage);
        nicknameErrorMessage.setText("");

        final AppManager profile = app;

        // Initialize radio groups used to select a colour and song.
        RadioGroup colours = findViewById(R.id.radioGroup1);
        RadioGroup songs = findViewById(R.id.radioGroup2);

        if (profile.getProfileColour() == Color.RED){
            colours.check(R.id.Red);
        }
        else if (profile.getProfileColour() == Color.BLUE){
            colours.check(R.id.Blue);
        }
        else{
            colours.check(R.id.Green);
        }

        if (profile.getProfileSong() == 0){
            songs.check(R.id.Song1);
        }
        else if (profile.getProfileSong() == 1){
            songs.check(R.id.Song2);
        }
        else{
            songs.check(R.id.nosong);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CustomizationActivity.this, StartActivity.class));

            }
        });

        final Activity thisActivity = this;

        red.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                profile.setProfileColour(thisActivity, Color.RED);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                profile.setProfileColour(thisActivity, Color.BLUE);
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                profile.setProfileColour(thisActivity, Color.GREEN);
            }
        });

        song1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                profile.setProfileSong(thisActivity, 0);
                app.changeMusic(0);
            }
        });

        song2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                profile.setProfileSong(thisActivity, 1);
                app.changeMusic(1);
            }
        });

        nosong.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                profile.setProfileSong(thisActivity, 3);
                 app.changeMusic(3);
            }
        });

        setNicknameButton.setOnClickListener(new View.OnClickListener() {
            final String VALID_NICKNAME_MESSAGE =  "New nickname set successfully.";
            final String INVALID_NICKNAME_MESSAGE = "Nicknames must be between 1-8 characters. No commas!";
            public void onClick(View v) {
                if (0 < nicknameEntry.getText().toString().length() &&
                        nicknameEntry.getText().toString().length() < 9 &&
                        !(nicknameEntry.getText().toString().contains(","))) {
                    profile.setProfileNickname(thisActivity, nicknameEntry.getText().toString());
                    nicknameErrorMessage.setText(VALID_NICKNAME_MESSAGE);
                } else {
                    nicknameErrorMessage.setText(INVALID_NICKNAME_MESSAGE);
                }
            }
        });
    }
}
