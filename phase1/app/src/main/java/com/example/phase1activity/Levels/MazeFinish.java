package com.example.phase1activity.Levels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.presentation.MainMenu.StartActivity;

public class MazeFinish extends AppCompatActivity implements View.OnClickListener{

    Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze_finish);

        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(MazeFinish.this, StartActivity.class));
    }
}
