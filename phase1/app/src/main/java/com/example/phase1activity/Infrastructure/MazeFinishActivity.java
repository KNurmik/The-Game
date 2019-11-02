package com.example.phase1activity.Infrastructure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase1activity.R;

/**
 * Interface that shows the congratulations screen after user wins the maze
 */
public class MazeFinishActivity extends AbstractActivities implements View.OnClickListener{

    public Button menuButton;
    public TextView nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze_finish);
        nickName = findViewById(R.id.textView4); // get the nickname from the profile customization
        String statement = app.getProfile().getNickname() + " finished the game."; // adds the nick name to the output statement
        nickName.setText(statement); // sets the text to statement
        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(this);
    }

    /**
     *
     * @param view The view of the maze finish xml
     */
    @Override
    public void onClick(View view) {
        app.getProfile().setGameLevel(this,0);
        startActivity(new Intent(MazeFinishActivity.this, StartActivity.class));
    }
}
