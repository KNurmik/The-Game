package com.example.phase1activity.ui.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phase1activity.R;
import com.example.phase1activity.ui.abstraction.AbstractActivity;
import com.example.phase1activity.ui.matching_game.MatchingInstructionsActivity;
import com.example.phase1activity.ui.maze_game.MazeInstructionsActivity;
import com.example.phase1activity.ui.reaction_game.ReactionInstructionsActivity;
import com.example.phase1activity.ui.leaderboard.LeaderboardViewImpl;

import static android.graphics.Color.rgb;

/**
 * Shows the start activity screen with three buttons. Statistics shows the users statistics Start
 * lets the user start the game Settings allows the user to customize their profile
 */
public class StartActivity extends AbstractActivity {
  /** The previously played level, set to 0 by default */
  int level;

  /** A numerical representation of the user's progress in the app */
  int userLevel;

  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);
    level = app.getProfileGameLevel();
    userLevel = app.getUserLevel();

    // Buttons on the screen.
    Button settingsButton = findViewById(R.id.settings);
    Button leaderButton = findViewById(R.id.stat);
    Button startButton = findViewById(R.id.start);
    Button globalButton = findViewById(R.id.global);
    Button startReact = findViewById(R.id.startReact);
    Button startMatch = findViewById(R.id.startMatch);
    Button startMaze = findViewById(R.id.startMaze);
    Button logOut = findViewById(R.id.logout);

    // Displaying user's level.
    TextView userLevelDisplay = findViewById(R.id.userLevelDisplay);
    ImageView levelBox = findViewById(R.id.levelBox);

    colourButton(levelBox, R.drawable.level_red, R.drawable.level_blue, R.drawable.level_green);

    userLevelDisplay.setText(Integer.toString(userLevel));
    int colour = app.getProfileColour();
    if (colour == Color.RED) {
      userLevelDisplay.setTextColor(rgb(113, 31, 31));
    } else if (colour == Color.BLUE) {
      userLevelDisplay.setTextColor(rgb(28, 57, 114));
    } else {
      userLevelDisplay.setTextColor(rgb(25, 99, 30));
    }

    // Set the color of each button to the color the user chooses in their profile.
    colourButton(
        startButton, R.drawable.resume_red, R.drawable.resume_blue, R.drawable.resume_green);
    colourButton(leaderButton, R.drawable.stat_red, R.drawable.stat_blue, R.drawable.stat_green);
    colourButton(
        globalButton,
        R.drawable.leaderboard_red,
        R.drawable.leaderboard_blue,
        R.drawable.leaderboard_green);

    // Add button functionality.
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
            startActivity(new Intent(StartActivity.this, PersonalStatsActivity.class));
            finish();
          }
        });

    globalButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            app.updateGlobalStats();
            startActivity(new Intent(StartActivity.this, LeaderboardViewImpl.class));
            finish();
          }
        });

    logOut.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            app.stopMusic();
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
            startActivity(new Intent(StartActivity.this, MazeInstructionsActivity.class));
            finish();
          }
        });

    // Open up the game user last played.
    startButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Go to the reaction game.
            if (level == 0) {
              startActivity(new Intent(StartActivity.this, ReactionInstructionsActivity.class));
              finish();
            }
            // Go to the matching game.
            else if (level == 1) {
              startActivity(new Intent(StartActivity.this, MatchingInstructionsActivity.class));
              finish();
            }
            // Go to the maze game.
            else if (level == 2) {
              startActivity(new Intent(StartActivity.this, MazeInstructionsActivity.class));
              finish();
            }
          }
        });
  }
}
