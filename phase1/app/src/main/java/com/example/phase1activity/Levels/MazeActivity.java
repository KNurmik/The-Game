package com.example.phase1activity.Levels;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.phase1activity.R;

public class MazeActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.phase1activity.Levels.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void startMazeGame(View view) {
        Intent intent = new Intent(this, TheMaze.class);
        // EditText editText = (EditText) findViewById(R.id.editText);
        // String message = editText.getText().toString();
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
