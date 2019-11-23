package com.example.phase1activity.UI.ReactionGame;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase1activity.Core.Transmission.ReactionGame.DaggerApplicationComponent;
import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivities;
import com.example.phase1activity.UI.MatchingGame.MatchingGameActivity;
import com.example.phase1activity.Core.Transmission.ReactionGame.ReactionGameModule;
import com.example.phase1activity.Core.Transmission.ReactionGame.ReactionGamePresenterInterface;
import com.example.phase1activity.UI.MenuScreens.StartActivity;

import javax.inject.Inject;

/** Activity for displaying ReactionGame. */
public class ReactionGameView extends AbstractActivities
    implements View.OnClickListener, ReactionGameViewInterface {
  @Inject public ReactionGamePresenterInterface presenter;
  Button btn;
  ColorStateList defaultColor;
  Button nextbtn;
  Button menu;

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
    menu = findViewById(R.id.button7);

    btn.setOnClickListener(this);
    colourButton(btn, R.drawable.reaction_red, R.drawable.reaction_blue, R.drawable.reaction_green);
    colourButton(menu, R.drawable.main_red, R.drawable.main_blue, R.drawable.main_green);
    colourButton(nextbtn, R.drawable.next_red, R.drawable.next_blue, R.drawable.next_green);

    TextView textView = findViewById(R.id.gameStateView);
    defaultColor = textView.getTextColors();

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

    updateGameStateView("Press the button to start the game.", defaultColor.getDefaultColor());
    updateScoreView(0);

    final Activity activity = this;

    // Button to skip the game.
    nextbtn.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            app.getProfile().setGameLevel(activity, 1);
            startActivity(new Intent(ReactionGameView.this, MatchingGameActivity.class));
          }
        });

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

  @Override
  public ColorStateList getColorStateList() {
    return defaultColor;
  }

  /**
   * Updates the text guiding the user.
   *
   * @param toThis text to set GameStateView to.
   * @param color colour to set GameStateView to.
   */
  public void updateGameStateView(String toThis, int color) {
    TextView textView = findViewById(R.id.gameStateView);
    textView.setText(toThis);
    textView.setTextColor(color);
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

  @Override
  public void updateProfileStatistics(double reactionTime, int moves, int score) {
    app.setProfileReactionTime(reactionTime);
    app.updateProfileMoves(moves);
    app.updateProfileScore(score);
  }

  /** End this activity and launch MatchingGame. */
  public void endActivity() {
    startActivity(new Intent(ReactionGameView.this, MatchingGameActivity.class));
  }
}
