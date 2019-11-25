package com.example.phase1activity.UI.MenuScreens;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivities;
import com.example.phase1activity.UI.MatchingGame.MatchingGameActivity;
import com.example.phase1activity.UI.MatchingGame.MatchingInstructionsActivity;
import com.example.phase1activity.UI.MazeGame.MazeMenuActivity;
import com.example.phase1activity.UI.ReactionGame.ReactionInstructionsActivity;

/**
 * Shows the start activity screen with three buttons.
 * Statistics shows the users statistics
 * Start lets the user start the game
 * Settings allows the user to customize their profile
 */
public class StartActivity extends AbstractActivities {
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        level = app.getProfileGameLevel();

        Button settingsbtn = findViewById(R.id.settings);
        Button leaderbtn = findViewById(R.id.stat);
        Button startButton = findViewById(R.id.start);

        colourButton(startButton, R.drawable.start_red, R.drawable.start_blue, R.drawable.start_green);
        colourButton(leaderbtn, R.drawable.stat_red, R.drawable.stat_blue, R.drawable.stat_green);
        colourButton(settingsbtn, R.drawable.settings_red, R.drawable.settings_blue, R.drawable.settings_green);

        settingsbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, CustomizationActivity.class));
                finish();
            }
        });

        leaderbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, LeaderboardActivity.class));
                finish();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Goes to the reaction game
                if(level == 0) {
                    startActivity(new Intent(StartActivity.this, ReactionInstructionsActivity.class));
                    finish();
                }
                //Goes to the matching game
                else if(level == 1){
                    startActivity(new Intent(StartActivity.this, MatchingInstructionsActivity.class));
                    finish();
                }
                //Goes to the maze game
                else if(level == 2){
                    startActivity(new Intent(StartActivity.this, MazeMenuActivity.class));
                    finish();
                }
            }
        });
        app.changeMusic(app.getProfileSong());


    }
}
