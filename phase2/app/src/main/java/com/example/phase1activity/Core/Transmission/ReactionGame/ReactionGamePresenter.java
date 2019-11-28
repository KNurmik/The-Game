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
 * ReactionGameManager and ReactionGameView. Tells view what to display based on computations done
 * by the manager.
 */
public class ReactionGamePresenter implements ReactionGamePresenterInterface {

  /** The manager responsible for the game's backend processes. */
  @Inject public ReactionGameManager manager;
  /** The view responsible for taking in user input and displaying things on the screen. */
  private ReactionGameViewInterface view;
  /** The number of times user has pressed the button (during a live turn). */
  private int totalClicks;
  /** The default colour of view. */
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
   * Called by ReactionGameView when user presses the button (in the middle of the screen). Asks
   * manager to deal with the button press, and displays on-screen info based on the state of the
   * game.
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
    } else if (manager.getGameState().equals(ReactionGameManager.State.SPAMBUTTON)) {
      manager.press();
      view.updateTestGameStateView(
          "CLICK THE BUTTON " + manager.getTimesToClickLeft() + " TIMES!!", Color.GREEN);
      view.updateGameStateView(R.drawable.react_spam);
      view.updateScoreView(manager.getScore());
      totalClicks += 1;
      view.updateTimeLeft("Time left: " + manager.getTimeLeft());
      if (manager.getGameState().equals(ReactionGameManager.State.BEGINNING)) {
        view.updateGameStateView(R.drawable.react_well);
      }
    }

    // Game is over.
    else if (manager.getGameState().equals(ReactionGameManager.State.GAMEOVER)) {
      view.endActivity();
    }
  }

  /**
   * Begin a turn of the game, if user has time left in the bank. Wait a random amount of time
   * between [0.5, 5] seconds, then prompt user to do something.
   *
   * <p>If there is no time left, display that the game is over, and save the stats the user
   * achieved.
   */
  private void beginGame() {

    // If user has time left in the bank.
    if (manager.isTimeLeft()) {

      manager.setGameState(ReactionGameManager.State.DONTREACT);
      view.updateGameStateView(R.drawable.react_dont);

      // Randomize time to wait until prompting user to do something.
      double random = 0.5 + Math.random() * 4500;
      new CountDownTimer((long) random, 1000) {

        public void onTick(long millisUntilFinished) {
          if (manager.getGameState().equals(ReactionGameManager.State.DONTREACT)) {
                double confuseRandom = Math.random();
                if (confuseRandom < 0.3) {
                    view.updateGameStateView(R.drawable.react_dont_trick);
                } else if (confuseRandom < 0.6) {
                    view.updateGameStateView(R.drawable.react_dont);
                }
            }
        }

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

  /** @param manager ReactionGameManager to set this.manager to. */
  public void setManager(ReactionGameManager manager) {
    this.manager = manager;
  }

}
