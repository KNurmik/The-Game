package com.example.phase1activity.Infrastructure;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase1activity.Domain.ReactionGameManager;
import com.example.phase1activity.R;

/**
 * Activity for displaying ReactionGame.
 */
public class ReactionGameActivity extends AbstractActivities implements View.OnClickListener {
    ReactionGameManager manager;
    Button btn;
    ColorStateList defaultColor;
    Button nextbtn;
    Button menu;
    int totalClicks;


    /**
     * Populates the screen with objects, and sets their functionality.
     *
     * @param savedInstanceState is the saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game);

        manager = new ReactionGameManager("easy");
        nextbtn = findViewById(R.id.Next);
        btn = findViewById(R.id.reactButton);
        menu = findViewById(R.id.button7);

        // Game is started from the beginning, reset all profile stats to default values.
        app.resetProfileMoves();
        app.resetProfileRxnStat();
        app.resetProfileScore();

        btn.setOnClickListener(this);
        btn.setBackgroundColor(app.getProfileColour());

        TextView textView = findViewById(R.id.gameStateView);
        defaultColor = textView.getTextColors();

        updateGameStateView("Press the button to start the game.", defaultColor.getDefaultColor());
        updateScoreView(app.getProfile().getNickname() + "'s score is: 0");

        final Activity activity = this;

        // Button to skip the game.
        nextbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                app.getProfile().setGameLevel(activity,1);
                startActivity(new Intent(ReactionGameActivity.this, MatchingGameActivity.class));
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReactionGameActivity.this, StartActivity.class));
            }
        });


    }

    /**
     * Deal with clicks on the button in the middle of the screen.
     */
    @Override
    public void onClick(View v) {

        // Turn hasn't stated, begin the game.
        if (manager.getGameState().equals("beginning")) {
            beginGame();
        }
        // Game has begun, user pressed button too early.
        else if (manager.getGameState().equals("do not react")) {
            manager.press();
            updateGameStateView("Too soon! Don't press the button!!", Color.RED);
            totalClicks += 1;
        }
        // User reacted correctly.
        else if (manager.getGameState().equals("react")) {
            manager.press();
            updateGameStateView("Well done!", Color.BLUE);
            updateScoreView(app.getProfile().getNickname() + "'s score is: " + manager.getScore());
            manager.setGameState("beginning");
            totalClicks += 1;
        }

        // Game is over.
        else if (manager.getGameState().equals("game over")) {
            startActivity(new Intent(ReactionGameActivity.this, MatchingGameActivity.class));
        }

    }

    /**
     * Updates the text guiding the user.
     *
     * @param toThis text to set GameStateView to.
     * @param color  colour to set GameStateView to.
     */
    public void updateGameStateView(String toThis, int color) {
        TextView textView = (TextView) findViewById(R.id.gameStateView);
        textView.setText(toThis);
        textView.setTextColor(color);
    }

    /**
     * Updates the text showing current score.
     *
     * @param toThis text to set ScoreView to.
     */
    public void updateScoreView(String toThis) {
        TextView textView = (TextView) findViewById(R.id.scoreView);
        textView.setText(toThis);
    }

    /**
     * Starts the game. If the user has not used up all of their time in the bank, it prompts the
     * user not to press the button. After a random amount of time between 0.5 and 5 seconds, the
     * user is prompted to react.
     * <p>
     * If the user has used up all of their time, "GAME OVER" is displayed and the user's final
     * score is shown.
     */
    void beginGame() {

        // If user has time left in the bank.
        if (manager.isTimeLeft()) {

            manager.setGameState("do not react");
            updateGameStateView("Don't press the button.", Color.MAGENTA);

            // Randomize time to wait until prompting user to
            double random = 0.5 + Math.random() * 4500;
            new CountDownTimer((long) random, 1000) {

                public void onTick(long millisUntilFinished) {
                    System.out.println("Time left: " + millisUntilFinished);
                }

                public void onFinish() {
                    manager.play();
                    // Update on-screen text.
                    updateGameStateView("PRESS THE BUTTON!", Color.GREEN);
                }
            }.start();

        }

        // User does not have time left in the bank.
        else {
            updateGameStateView("GAME OVER", defaultColor.getDefaultColor());
            manager.setGameState("game over");
            app.setProfileReactionTime(manager.getFastestReaction());
            app.updateProfileMoves(totalClicks);
            app.updateProfileScore(manager.getScore());
            updateScoreView(app.getProfile().getNickname() + "'s score is: " + manager.getScore());
        }
    }


}
