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

import com.example.phase1activity.Domain.ReactionGame.ReactionGameManager;
import com.example.phase1activity.R;

/**
 * Activity for displaying ReactionGame.
 */
public class ReactionGameView extends AbstractActivities implements View.OnClickListener, ReactionGameViewInterface {
    ReactionGamePresenterInterface presenter;
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
        btn.setBackgroundColor(app.getProfileColour());

        TextView textView = findViewById(R.id.gameStateView);
        defaultColor = textView.getTextColors();

        // TODO: inject presenter.
        presenter = new ReactionGamePresenter(this, defaultColor);
        // Game is started from the beginning, reset all profile stats to default values.
        app.resetProfileMoves();
        app.resetProfileRxnStat();
        app.resetProfileScore();

        updateGameStateView("Press the button to start the game.", defaultColor.getDefaultColor());
        updateScoreView(0);

        final Activity activity = this;

        // Button to skip the game.
        nextbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                app.getProfile().setGameLevel(activity, 1);
                startActivity(new Intent(ReactionGameView.this, MatchingGameActivity.class));
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReactionGameView.this, StartActivity.class));
            }
        });


    }

    /**
     * Deal with clicks on the button in the middle of the screen.
     */
    @Override
    public void onClick(View v) {
        presenter.handleClick();
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
     * @param toThisScore score to set ScoreView to show.
     */
    public void updateScoreView(int toThisScore) {
        String toThis = app.getProfileNickname() + "'s score is: " + toThisScore;
        TextView textView = (TextView) findViewById(R.id.scoreView);
        textView.setText(toThis);
    }

    @Override
    public void updateProfileStatistics(double reactionTime, int moves, int score) {
        app.setProfileReactionTime(reactionTime);
        app.updateProfileMoves(moves);
        app.updateProfileScore(score);
    }

    /**
     * End this activity and launch MatchingGame.
     */
    public void endActivity() {
        startActivity(new Intent(ReactionGameView.this, MatchingGameActivity.class));
    }


}
