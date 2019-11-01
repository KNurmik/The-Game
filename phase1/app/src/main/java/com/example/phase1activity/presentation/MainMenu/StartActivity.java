package com.example.phase1activity.presentation.MainMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.AbstractActivities;
import com.example.phase1activity.Levels.MazeActivity;
import com.example.phase1activity.MatchingGameActivity;
import com.example.phase1activity.Profile.CustomizationActivity;
import com.example.phase1activity.R;
import com.example.phase1activity.Levels.ReactionGame.ReactionInstructionsActivity;

public class StartActivity extends AbstractActivities {
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        level = app.getProfile().getGameLevel();


        Button settingsbtn = findViewById(R.id.settings);
        Button leaderbtn = findViewById(R.id.Leaderboard);
        Button startButton = findViewById(R.id.Start);


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
                if(level == 0) {
                    startActivity(new Intent(StartActivity.this, ReactionInstructionsActivity.class));
                    finish();
                }
                else if(level == 1){
                    startActivity(new Intent(StartActivity.this, MatchingGameActivity.class));
                    finish();
                }
                else if(level == 2){
                    startActivity(new Intent(StartActivity.this, MazeActivity.class));
                    finish();
                }
            }
        });

    }
}
