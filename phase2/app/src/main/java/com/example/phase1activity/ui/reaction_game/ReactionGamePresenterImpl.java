package com.example.phase1activity.ui.reaction_game;

import android.content.res.ColorStateList;
import android.os.CountDownTimer;

import com.example.phase1activity.domain.reaction_game.ReactionGameManager;
import com.example.phase1activity.domain.reaction_game.ReactionGameManagerImpl;
import com.example.phase1activity.R;

import javax.inject.Inject;

/**
 * Class responsible for "presenter" role in MVP architecture. Serves as a middleman between
 * ReactionGameManagerImpl and ReactionGameViewImpl. Tells view what to display based on
 * computations done by the manager.
 */
public class ReactionGamePresenterImpl implements ReactionGamePresenter {

  /** The manager responsible for the game's backend processes. */
  @Inject public ReactionGameManager manager;
  /** The view responsible for taking in user input and displaying things on the screen. */
  private ReactionGameView view;
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
  public ReactionGamePresenterImpl(ReactionGameView v, ColorStateList def) {
    defaultColour = def;
    view = v;
  }

  /**
   * Called by ReactionGameViewImpl when user presses the button (in the middle of the screen). Asks
   * manager to deal with the button press, and displays on-screen info based on the state of the
   * game.
   */
  public void handleClick() {

    // Turn hasn't stated, begin the game.
    if (manager.getGameState().equals(ReactionGameManagerImpl.State.BEGINNING)) {
      beginGame();
    }
    // Game has begun, user pressed button too early.
    else if (manager.getGameState().equals(ReactionGameManagerImpl.State.DONTREACT)) {
      manager.press();
      view.updateGameStateView(R.drawable.react_soon);
      totalClicks += 1;
      view.updateTimeLeft(manager.getTimeLeft());
    }
    // User reacted correctly.
    else if (manager.getGameState().equals(ReactionGameManagerImpl.State.REACT)) {
      manager.press();
      view.updateGameStateView(R.drawable.react_well);
      view.updateScoreView(manager.getScore());
      manager.setGameState(ReactionGameManagerImpl.State.BEGINNING);
      totalClicks += 1;
      view.updateTimeLeft(manager.getTimeLeft());
    }
    // User has to spam button.
    else if (manager.getGameState().equals(ReactionGameManagerImpl.State.SPAMBUTTON)) {
      manager.press();
      view.updateGameStateView(R.drawable.react_spam);
      view.updateScoreView(manager.getScore());
      totalClicks += 1;
      view.updateTimeLeft(manager.getTimeLeft());
      // User has spammed enough.
      if (manager.getGameState().equals(ReactionGameManagerImpl.State.BEGINNING)) {
        view.updateGameStateView(R.drawable.react_stop);
        view.disableButton();
        new CountDownTimer(1000, 1000) {

          public void onTick(long millisUntilFinished) {}

          public void onFinish() {
            view.updateGameStateView(R.drawable.react_well);
            view.enableButton();
            view.updateTimeLeft(manager.getTimeLeft());
          }
        }.start();
      }
    }

    // Game is over.
    else if (manager.getGameState().equals(ReactionGameManagerImpl.State.GAMEOVER)) {
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

      manager.setGameState(ReactionGameManagerImpl.State.DONTREACT);
      view.updateGameStateView(R.drawable.react_dont);
      view.updateTimeLeft(manager.getTimeLeft());

      // Randomize time to wait until prompting user to do something.
      double random = 0.5 + Math.random() * 4500;
      new CountDownTimer((long) random, 1000) {

        public void onTick(long millisUntilFinished) {
          if (manager.getGameState().equals(ReactionGameManagerImpl.State.DONTREACT)) {
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
            view.updateGameStateView(R.drawable.react_spam);
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
      manager.setGameState(ReactionGameManagerImpl.State.GAMEOVER);
      view.updateProfileStatistics(manager.getFastestReaction(), totalClicks, manager.getScore());
      view.updateScoreView(manager.getScore());
    }
  }

  /** @param manager ReactionGameManagerImpl to set this.manager to. */
  public void setManager(ReactionGameManagerImpl manager) {
    this.manager = manager;
  }
}
