package com.example.phase1activity.ui.reaction_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.ui.abstraction.AbstractActivity;

/** Activity for displaying instructions to ReactionGame. */
public class ReactionInstructionsActivity extends AbstractActivity {

  /**
   * Populate the screen with objects. Allow user to continue to the game using the button.
   *
   * @param savedInstanceState the saved instance state.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reaction_instructions);

    Button btn = findViewById(R.id.startGame);
    colourButton(btn, R.drawable.start_red, R.drawable.start_blue, R.drawable.start_green);

    btn.setOnClickListener(
        new View.OnClickListener() {
          /** Allow user to continue to the game using the button. */
          @Override
          public void onClick(View v) {
            startActivity(
                new Intent(ReactionInstructionsActivity.this, ReactionGameViewImpl.class));
          }
        });
  }
}
