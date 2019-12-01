package com.example.phase1activity.ui.reaction_game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.example.phase1activity.R;
import com.example.phase1activity.ui.abstraction.AbstractActivity;
import com.example.phase1activity.ui.matching_game.MatchingInstructionsActivity;
import com.example.phase1activity.ui.menu.StartActivity;

import javax.inject.Inject;

/** Activity for displaying ReactionGame. */
public class ReactionGameViewImpl extends AbstractActivity
    implements View.OnClickListener, ReactionGameView {

  /** Presenter object responsible for handling user input. Injected using Dagger. */
  @Inject public ReactionGamePresenter presenter;
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
  /** ProgressBar saying how much time user has left in the bank. */
  ProgressBar timeLeft;

  /**
   * Populates the screen with objects, and sets their functionality.
   *
   * @param savedInstanceState is the saved instance state.
   */
  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reaction_game);

    nextbtn = findViewById(R.id.Next);
    btn = findViewById(R.id.reactButton);
    menu = findViewById(R.id.button12);
    timeLeft = findViewById(R.id.timeLeft);
    timeLeft.setProgress(100);

    btn.setOnClickListener(this);
    colourButton(btn, R.drawable.reaction_red, R.drawable.reaction_blue, R.drawable.reaction_green);
    colourButton(menu, R.drawable.main_red, R.drawable.main_blue, R.drawable.main_green);
    colourButton(nextbtn, R.drawable.next_red, R.drawable.next_blue, R.drawable.next_green);

    // Dependency injection using Dagger.
    presenter =
        DaggerReactionGameComponent.builder()
            .reactionGameModule(new ReactionGameModule(this))
            .build()
            .injectReactionGamePresenter();

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
            startActivity(
                new Intent(ReactionGameViewImpl.this, MatchingInstructionsActivity.class));
          }
        });

    // Button to return to the main menu.
    menu.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            startActivity(new Intent(ReactionGameViewImpl.this, StartActivity.class));
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

  /**
   * Update the progress bar displayed below the button to displau time left
   *
   * @param time The amount of time remaining
   */
  public void updateTimeLeft(double time) {
    int fraction = (int) (100 * time / 8000);
    timeLeft.setProgress(Math.min(100, fraction));
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
    app.setProfileReactionTime(this, reactionTime / 1000);
    app.updateProfileMoves(this, moves);
    app.updateProfileScore(this, score);
  }

  /** Disable btn. */
  public void disableButton() {
    btn.setEnabled(false);
  }

  /** Re-enable button. */
  public void enableButton() {
    btn.setEnabled(true);
  }

  /** End this activity and launch MatchingGame. */
  public void endActivity() {
    startActivity(new Intent(ReactionGameViewImpl.this, StartActivity.class));
  }
}
