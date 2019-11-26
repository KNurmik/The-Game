package com.example.phase1activity.Core.Transmission.ReactionGame;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;

import com.example.phase1activity.Core.Logic.ReactionGame.ReactionGameManager;
import com.example.phase1activity.R;
import com.example.phase1activity.UI.ReactionGame.ReactionGameViewInterface;

import javax.inject.Inject;

/**
 * Class responsible for "presenter" role in MVP architecture. Serves as a middleman between
 * ReactionGameManager and ReactionGameView.
 */
public class ReactionGamePresenter implements ReactionGamePresenterInterface {

  @Inject
  public ReactionGameManager manager;
  private ReactionGameViewInterface view;
  private int totalClicks;
  private ColorStateList defaultColour;

  /**
   * Create presenter. Tell Dagger how to create this object.
   *
   * @param v the activity this presenter will serve as a middleman to.
   * @param def defaultColour of v.
   */
  @Inject
  public ReactionGamePresenter(ReactionGameViewInterface v, ColorStateList def) {
    defaultColour = def;
    view = v;
  }

  /**
   * Called by ReactionGameView when user presses the button (in the middle of the screen). Calls
   * appropriate methods based on the state of the game.
   */
  public void handleClick() {
    view.updateTimeLeft("Time left: " + manager.getTimeLeft());

    // Turn hasn't stated, begin the game.
    if (manager.getGameState().equals(ReactionGameManager.State.BEGINNING)) {
      beginGame();
    }
    // Game has begun, user pressed button too early.
    else if (manager.getGameState().equals(ReactionGameManager.State.DONTREACT)) {
      manager.press();
      view.updateGameStateView(R.drawable.react_soon);
      totalClicks += 1;
      view.updateTimeLeft("Time left: " + manager.getTimeLeft());
    }
    // User reacted correctly.
    else if (manager.getGameState().equals(ReactionGameManager.State.REACT)) {
      manager.press();
      view.updateGameStateView(R.drawable.react_well);
      view.updateScoreView(manager.getScore());
      manager.setGameState(ReactionGameManager.State.BEGINNING);
      totalClicks += 1;
      view.updateTimeLeft("Time left: " + manager.getTimeLeft());
    }

    else if(manager.getGameState().equals(ReactionGameManager.State.SPAMBUTTON)){
      manager.press();
      view.updateTestGameStateView("CLICK THE BUTTON " + manager.getTimesToClickLeft() + " TIMES!!", Color.GREEN);
      view.updateScoreView(manager.getScore());
      totalClicks += 1;
      view.updateTimeLeft("Time left: " + manager.getTimeLeft());
      if(manager.getGameState().equals(ReactionGameManager.State.BEGINNING)){
        view.updateGameStateView(R.drawable.react_well);
      }
    }

    // Game is over.
    else if (manager.getGameState().equals(ReactionGameManager.State.GAMEOVER)) {
      view.endActivity();
    }
  }

  private void beginGame() {

    // If user has time left in the bank.
    if (manager.isTimeLeft()) {

      manager.setGameState(ReactionGameManager.State.DONTREACT);
      view.updateGameStateView(R.drawable.react_dont);

      // Randomize time to wait until prompting user to
      double random = 0.5 + Math.random() * 4500;
      new CountDownTimer((long) random, 1000) {

        public void onTick(long millisUntilFinished) {}

        public void onFinish() {
          double r = Math.random();
          if (r < 0.15) {
            manager.playSpamButton();
            view.updateTestGameStateView(
                "CLICK THE BUTTON " + manager.getTimesToClickLeft() + " TIMES!!", Color.GREEN);
            view.updateGameStateView(R.drawable.react_push);
          } else {
            manager.playSimpleReaction();
            // Update on-screen text.
            view.updateGameStateView(R.drawable.react_push);
          }
        }
      }.start();

    }

    // User does not have time left in the bank.
    else {
      view.updateGameStateView(R.drawable.react_end);
      manager.setGameState(ReactionGameManager.State.GAMEOVER);
      view.updateProfileStatistics(manager.getFastestReaction(), totalClicks, manager.getScore());
      view.updateScoreView(manager.getScore());
    }
  }

  public void setManager(ReactionGameManager manager) {
    this.manager = manager;
  }

}
