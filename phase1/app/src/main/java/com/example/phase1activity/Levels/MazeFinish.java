package com.example.phase1activity.Levels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.AbstractActivities;
import com.example.phase1activity.R;
import com.example.phase1activity.presentation.MainMenu.StartActivity;

public class MazeFinish extends AbstractActivities implements View.OnClickListener{

    Button menuButton;
    public String playerNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze_finish);
        playerNickname = app.getProfile().getNickname();
        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        app.getProfile().setGameLevel(0);
        startActivity(new Intent(MazeFinish.this, StartActivity.class));
    }
}
