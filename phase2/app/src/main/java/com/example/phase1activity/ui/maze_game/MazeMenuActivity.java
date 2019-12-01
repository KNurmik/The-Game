package com.example.phase1activity.ui.maze_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.ui.abstraction.AbstractActivity;

/**
 * The activity that is displayed after completing the matching game, before starting the maze itself
 */
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
                        app.setMazeGameDifficulty(true);
                        startActivity(new Intent(MazeMenuActivity.this, MazeGameActivity.class));
                    }
                });
        hardButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        app.setMazeGameDifficulty(false);
                        startActivity(new Intent(MazeMenuActivity.this, MazeGameActivity.class));
                    }
                });
    }



}
