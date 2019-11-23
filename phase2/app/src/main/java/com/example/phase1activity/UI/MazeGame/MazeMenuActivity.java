package com.example.phase1activity.UI.MazeGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivities;

/**
 * The activity that is displayed after completing the matching game, before starting the maze itself
 */
public class MazeMenuActivity extends AbstractActivities {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze_menu);

        Button startBtn = findViewById(R.id.startBtn);
        colourButton(startBtn, R.drawable.start_red, R.drawable.start_blue, R.drawable.start_green);
    }

    /**
     * Called when the user taps the Send button
     */
    public void startMazeGame(View view) {
        Intent intent = new Intent(this, MazeGameActivity.class);
        // EditText editText = (EditText) findViewById(R.id.editText);
        // String message = editText.getText().toString();
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
