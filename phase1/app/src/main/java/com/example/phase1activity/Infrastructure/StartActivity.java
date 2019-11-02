package com.example.phase1activity.Infrastructure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;


public class StartActivity extends AbstractActivities {
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        level = app.getProfile().getGameLevel();


        Button settingsbtn = findViewById(R.id.settings);
        Button leaderbtn = findViewById(R.id.stat);
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
