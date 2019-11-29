package com.example.phase1activity.UI.MenuScreens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivity;
import com.example.phase1activity.UI.MatchingGame.MatchingInstructionsActivity;
import com.example.phase1activity.UI.MazeGame.MazeMenuActivity;
import com.example.phase1activity.UI.ReactionGame.ReactionInstructionsActivity;

/**
 * Shows the start activity screen with three buttons. Statistics shows the users statistics Start
 * lets the user start the game Settings allows the user to customize their profile
 */
public class StartActivity extends AbstractActivity {
  int level;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);
    level = app.getProfileGameLevel();

    Button settingsButton = findViewById(R.id.settings);
    Button leaderButton = findViewById(R.id.stat);
    Button startButton = findViewById(R.id.start);
    Button globalButton = findViewById(R.id.global);
    Button startReact = findViewById(R.id.startReact);
    Button startMatch = findViewById(R.id.startMatch);
    Button startMaze = findViewById(R.id.startMaze);

    // Sets the color of each button to the color the user chooses in his/her profile
    colourButton(startButton, R.drawable.resume_red, R.drawable.resume_blue, R.drawable.resume_green);
    colourButton(leaderButton, R.drawable.stat_red, R.drawable.stat_blue, R.drawable.stat_green);
    colourButton(
        settingsButton,
        R.drawable.settings_red,
        R.drawable.settings_blue,
        R.drawable.settings_green);
    colourButton(
        globalButton,
        R.drawable.leaderboard_red,
        R.drawable.leaderboard_blue,
        R.drawable.leaderboard_green);

    settingsButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            startActivity(new Intent(StartActivity.this, CustomizationActivity.class));
            finish();
          }
        });

    leaderButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            startActivity(new Intent(StartActivity.this, CurrentStatsActivity.class));
            finish();
          }
        });

    globalButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            app.updateGlobalStats();
            startActivity(new Intent(StartActivity.this, GlobalStatsActivity.class));
            finish();
          }
        });

    final Activity activity = this;

    startReact.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            app.setProfileGameLevel(activity, 0);
            startActivity(new Intent(StartActivity.this, ReactionInstructionsActivity.class));
            finish();
          }
        });

    startMatch.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            app.setProfileGameLevel(activity, 1);
            startActivity(new Intent(StartActivity.this, MatchingInstructionsActivity.class));
            finish();
          }
        });

    startMaze.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            app.setProfileGameLevel(activity, 2);
            startActivity(new Intent(StartActivity.this, MazeMenuActivity.class));
            finish();
          }
        });

    startButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Goes to the reaction game
            if (level == 0) {
              startActivity(new Intent(StartActivity.this, ReactionInstructionsActivity.class));
              finish();
            }
            // Goes to the matching game
            else if (level == 1) {
              startActivity(new Intent(StartActivity.this, MatchingInstructionsActivity.class));
              finish();
            }
            // Goes to the maze game
            else if (level == 2) {
              startActivity(new Intent(StartActivity.this, MazeMenuActivity.class));
              finish();
            }
          }
        });
  }
}
