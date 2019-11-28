/*
 * Copyright 2019. Mark Vartolaş, Karl Hendrik Nurmeots, Olivia Li, Ramzi Dajani, Henry Leung.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.phase1activity.UI.ReactionGame;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.example.phase1activity.Core.Transmission.ReactionGame.DaggerApplicationComponent;
import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivity;
import com.example.phase1activity.Core.Transmission.ReactionGame.ReactionGameModule;
import com.example.phase1activity.Core.Transmission.ReactionGame.ReactionGamePresenterInterface;
import com.example.phase1activity.UI.MatchingGame.MatchingInstructionsActivity;
import com.example.phase1activity.UI.MenuScreens.StartActivity;

import javax.inject.Inject;

/** Activity for displaying ReactionGame. */
public class ReactionGameView extends AbstractActivity
    implements View.OnClickListener, ReactionGameViewInterface {

  /** Presenter object responsible for handling user input. Injected using Dagger. */
  @Inject public ReactionGamePresenterInterface presenter;
  /** The main button in the middle of the screen. Used for playing the game. */
  Button btn;
  /** The default colour of the view. */
  ColorStateList defaultColor;
  /** Button for skipping the game. */
  Button nextbtn;
  /** Button for returning to the main menu. */
  Button menu;
  /** Pictures instructing the user. */
  ImageView instructions;

  /**
   * Populates the screen with objects, and sets their functionality.
   *
   * @param savedInstanceState is the saved instance state.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reaction_game);

    nextbtn = findViewById(R.id.Next);
    btn = findViewById(R.id.reactButton);
    menu = findViewById(R.id.button12);

    btn.setOnClickListener(this);
    colourButton(btn, R.drawable.reaction_red, R.drawable.reaction_blue, R.drawable.reaction_green);
    colourButton(menu, R.drawable.main_red, R.drawable.main_blue, R.drawable.main_green);
    colourButton(nextbtn, R.drawable.next_red, R.drawable.next_blue, R.drawable.next_green);

    // Dependency injection using Dagger.
    presenter =
        DaggerApplicationComponent.builder()
            .reactionGameModule(new ReactionGameModule(this))
            .build()
            .injectReactionGamePresenter();

    // Game is started from the beginning, reset all profile stats to default values.
    app.resetProfileMoves();
    app.resetProfileRxnStat();
    app.resetProfileScore();

    instructions = findViewById(R.id.instruction);
    instructions.setBackgroundResource(R.drawable.react_push);
    updateGameStateView(R.drawable.react_push);
    updateScoreView(0);

    final Activity activity = this;

    // Button to skip the game.
    nextbtn.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            app.setProfileGameLevel(activity, 1);
            startActivity(new Intent(ReactionGameView.this, MatchingInstructionsActivity.class));
          }
        });

    // Button to return to the main menu.
    menu.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            startActivity(new Intent(ReactionGameView.this, StartActivity.class));
          }
        });
  }

  /** Deal with clicks on the button in the middle of the screen. */
  @Override
  public void onClick(View v) {
    presenter.handleClick();
  }

  /** @return defaultColor. */
  @Override
  public ColorStateList getColorStateList() {
    return defaultColor;
  }

  /**
   * Update text displayed in the middle of the screen to guide user.
   *
   * @param newState The image resource displayed for the instructions
   */
  public void updateGameStateView(@DrawableRes int newState) {
    instructions.setBackgroundResource(newState);
  }

  // TODO: READ THE COMMENT BELOW!!!!!!!!!!!!!!!!!!
  // TODO: THIS IS A TESTING METHOD, DELETE ME ONCE FINISHED!!!!!!!!!!!!!!!!!!!!!!!!!!
  public void updateTestGameStateView(String toThis, int colour) {
    TextView text = findViewById(R.id.testInstruction);
    text.setText(toThis);
    text.setTextColor(colour);
  }

  public void updateTimeLeft(String toThis) {
    TextView text = findViewById(R.id.timeLeft);
    text.setText(toThis);
  }

  /**
   * Updates the text showing current score.
   *
   * @param toThisScore score to set ScoreView to show.
   */
  public void updateScoreView(int toThisScore) {
    String toThis = app.getProfileNickname() + "'s score is: " + toThisScore;
    TextView textView = findViewById(R.id.scoreView);
    textView.setText(toThis);
  }

  /**
   * Access AppManager and let it know of the user's statistics.
   *
   * @param reactionTime fastest reaction time of user.
   * @param moves number of moves user took (i.e. the number of times user pressed the button to
   *     react).
   * @param score user's score.
   */
  @Override
  public void updateProfileStatistics(double reactionTime, int moves, int score) {
    app.setProfileReactionTime(reactionTime);
    app.updateProfileMoves(moves);
    app.updateProfileScore(score);
  }

  /** End this activity and launch MatchingGame. */
  public void endActivity() {
    startActivity(new Intent(ReactionGameView.this, MatchingInstructionsActivity.class));
  }
}
