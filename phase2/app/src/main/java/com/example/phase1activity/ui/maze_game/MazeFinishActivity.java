package com.example.phase1activity.ui.maze_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase1activity.R;
import com.example.phase1activity.ui.abstraction.AbstractActivity;
import com.example.phase1activity.ui.menu.StartActivity;

/** Activity that shows the congratulations screen after user wins the maze */
public class MazeFinishActivity extends AbstractActivity implements View.OnClickListener {

  /** Button for returning to the main menu. */
  public Button menuButton;
  /** TextView showing that the user with given nickname has finished the game. */
  public TextView nickName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maze_finish);
    nickName = findViewById(R.id.textView4); // get the nickname from the profile customization
    String statement =
        app.getProfileNickname()
            + " finished the game."; // adds the nick name to the output statement
    nickName.setText(statement); // sets the text to statement

    menuButton = findViewById(R.id.menuButton);
    menuButton.setOnClickListener(this);
    colourButton(menuButton, R.drawable.main_red, R.drawable.main_blue, R.drawable.main_green);
  }

  /** @param view The view of the maze finish xml */
  @Override
  public void onClick(View view) {
    app.setProfileGameLevel(this, 0);
    startActivity(new Intent(MazeFinishActivity.this, StartActivity.class));
  }
}
