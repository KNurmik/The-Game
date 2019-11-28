package com.example.phase1activity.UI.MenuScreens;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivity;
import com.example.phase1activity.Core.Transmission.Saving.AndroidSaver;
import com.example.phase1activity.Core.Transmission.Saving.ISaver;

/**
 * Customization Activity
 */
public class CustomizationActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);

        //Initializes all the buttons and text fields
        RadioButton red = findViewById(R.id.Red);
        RadioButton green = findViewById(R.id.Green);
        RadioButton blue = findViewById(R.id.Blue);
        RadioButton song1 = findViewById(R.id.Song1);
        RadioButton song2 = findViewById(R.id.Song2);
        Button set = findViewById(R.id.set);
        final EditText name = findViewById(R.id.nickname);
        Button back = findViewById(R.id.menu);

        final AppManager profile = app;

        /**
         * The two radio groups used to select a colour and song
         */
        RadioGroup colours = findViewById(R.id.radioGroup1);
        RadioGroup songs = findViewById(R.id.radioGroup2);

        //Whichever color the user selects for their profile should be the default option when
        //opening the CustomizationActivity
        if (profile.getProfileColour() == Color.RED){
            colours.check(R.id.Red);
        }
        else if (profile.getProfileColour() == Color.BLUE){
            colours.check(R.id.Blue);
        }
        else{
            colours.check(R.id.Green);
        }

        //Whichever song the user selects for their profile should be the default option when
        //opening the CustomizationActivity
        if (profile.getProfileSong() == 0){
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
                //Sets the song to song0
                profile.setProfileSong(thisActivity, 0);
                app.changeMusic(0);
            }
        });

        song2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Sets the song to song1
                profile.setProfileSong(thisActivity, 1);
                app.changeMusic(1);
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                profile.setProfileNickname(thisActivity, name.getText().toString());
            }
        });
    }

//    void saveProfileData(AppManager profile) {
//        ISaver iSaver = new AndroidSaver(this.getApplicationContext());
//        iSaver.saveData(profile.getProfileUsername() + "," + profile.getProfilePassword() + "," + profile.getProfileNickname() + "," + profile.getProfileColour() + "," + profile.getProfileSong() + "," + profile.getProfileGameLevel() + "," + profile.getProfileTotalScoreStat() + "," + profile.getProfileFastestRxnStat() + "," + profile.getProfileTotalMovesStat());
//    }
}
