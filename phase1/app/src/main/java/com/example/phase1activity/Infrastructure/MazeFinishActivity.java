package com.example.phase1activity.Infrastructure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase1activity.R;

public class MazeFinishActivity extends AbstractActivities implements View.OnClickListener{

    public Button menuButton;
    public TextView nickName;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze_finish);
        nickName = findViewById(R.id.textView4);
        String statement = app.getProfile().getNickname() + " finished the game.";
        nickName.setText(statement);
        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        app.getProfile().setGameLevel(this,0);
        startActivity(new Intent(MazeFinishActivity.this, StartActivity.class));
    }
}
