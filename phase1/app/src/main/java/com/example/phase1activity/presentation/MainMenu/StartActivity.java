package com.example.phase1activity.presentation.MainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.Profile.CustomizationActivity;
import com.example.phase1activity.R;
import com.example.phase1activity.presentation.LogIn.LogInActivity;
import com.example.phase1activity.Levels.ReactionGame.ReactionInstructionsActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button settingsbtn = findViewById(R.id.settings);
        Button leaderbtn = findViewById(R.id.Leaderboard);
        Button startButton = findViewById(R.id.Start);

        settingsbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, CustomizationActivity.class));
            }
        });

        leaderbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, LeaderboardActivity.class));
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, ReactionInstructionsActivity.class));
            }
        });

    }
}
