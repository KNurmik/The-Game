package com.example.phase1activity.ui.maze_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.ui.abstraction.AbstractActivity;

/** Activity allowing user to choose what difficulty they want to play MazeGame with. */
public class MazeMenuActivity extends AbstractActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maze_menu);

    Button easyButton = findViewById(R.id.easyBtn);
    Button hardButton = findViewById(R.id.extremeBtn);

    easyButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            appManager.setMazeGameDifficulty(true);
            startActivity(new Intent(MazeMenuActivity.this, MazeGameViewImpl.class));
          }
        });
    hardButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            appManager.setMazeGameDifficulty(false);
            startActivity(new Intent(MazeMenuActivity.this, MazeGameViewImpl.class));
          }
        });
  }
}
