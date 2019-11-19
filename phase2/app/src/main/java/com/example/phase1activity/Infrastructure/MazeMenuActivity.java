package com.example.phase1activity.Infrastructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.phase1activity.R;

/**
 * The activity that is displayed after completing the matching game, before starting the maze itself
 */
public class MazeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze_menu);
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
