package com.example.phase1activity.Infrastructure;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;

import com.example.phase1activity.Domain.Overseers.AppManager;
import com.example.phase1activity.Domain.ReactionGame.ReactionGameManager;

/**
 * Class responsible for "presenter" role in MVP architecture. Serves as a middleman between
 * ReactionGameManager and ReactionGameView.
 */
public class ReactionGamePresenter {

    private ReactionGameManager manager = new ReactionGameManager("easy");
    private ReactionGameView view;
    private int totalClicks;
    private ColorStateList defaultColour;

    public ReactionGamePresenter(ReactionGameView v, ColorStateList def) {
        defaultColour = def;
        view = v;
    }

    /**
     * Called by ReactionGameView when user presses the button (in the middle of the screen). Calls
     * appropriate methods based on the state of the game.
     */
    public void handleClick() {

        // Turn hasn't stated, begin the game.
        if (manager.getGameState().equals("beginning")) {
            beginGame();
        }
        // Game has begun, user pressed button too early.
        else if (manager.getGameState().equals("do not react")) {
            manager.press();
            view.updateGameStateView("Too soon! Don't press the button!!", Color.RED);
            totalClicks += 1;
        }
        // User reacted correctly.
        else if (manager.getGameState().equals("react")) {
            manager.press();
            view.updateGameStateView("Well done!", Color.BLUE);
            view.updateScoreView(view.getAppManager().getProfile().getNickname() + "'s score is: " + manager.getScore());
            manager.setGameState("beginning");
            totalClicks += 1;
        }

        // Game is over.
        else if (manager.getGameState().equals("game over")) {
            view.endActivity();
        }
    }

    /**
     * The game has been started from the beginning, reset all profile stats.
     */
    public void resetProfileStats() {
        AppManager app = view.getAppManager();
        app.resetProfileMoves();
        app.resetProfileRxnStat();
        app.resetProfileScore();
    }

    private void beginGame() {

        // If user has time left in the bank.
        if (manager.isTimeLeft()) {

            manager.setGameState("do not react");
            view.updateGameStateView("Don't press the button.", Color.MAGENTA);

            // Randomize time to wait until prompting user to
            double random = 0.5 + Math.random() * 4500;
            new CountDownTimer((long) random, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    manager.play();
                    // Update on-screen text.
                    view.updateGameStateView("PRESS THE BUTTON!", Color.GREEN);
                }
            }.start();

        }

        // User does not have time left in the bank.
        else {
            view.updateGameStateView("GAME OVER", defaultColour.getDefaultColor());
            manager.setGameState("game over");
            AppManager app = view.getAppManager();
            app.setProfileReactionTime(manager.getFastestReaction());
            app.updateProfileMoves(totalClicks);
            app.updateProfileScore(manager.getScore());
            view.updateScoreView(app.getProfileNickname() + "'s score is: " + manager.getScore());
        }
    }

}
