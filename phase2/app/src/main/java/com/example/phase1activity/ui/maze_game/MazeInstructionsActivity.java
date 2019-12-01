package com.example.phase1activity.ui.maze_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.ui.abstraction.AbstractActivity;

public class MazeInstructionsActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze_instructions);

        Button btn = findViewById(R.id.startGame);
        colourButton(btn, R.drawable.next_red, R.drawable.next_blue, R.drawable.next_green);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    /** Allow user to continue to the game using the button. */
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MazeInstructionsActivity.this, MazeMenuActivity.class));
                    }
                });
    }
}
