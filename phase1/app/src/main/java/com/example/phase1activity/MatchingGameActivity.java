package com.example.phase1activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase1activity.Levels.MazeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * A MatchingGameActivity.
 */
public class MatchingGameActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * This MatchingGameActivity's MatchingGameManager
     */
    private MatchingGameManager manager;

    /**
     * The string to be displayed on the back of each card.
     */
    final static String BACKOFCARD = "CLICK ME!";

    /**
     * The string to be displayed next to the number of turns taken.
     */
    final static String TURNSTAKEN = "Turns Taken: ";

    /**
     * The string to be displayed next to the final score.
     */
    final static String SCORE = "Final Score: ";

    /**
     * The string to be displayed on the Next Level button.
     */
    final static String NEXT = "Next Level";

    /**
     * A map of this MatchingGameActivity's cards to their respective values.
     */
    Map<Button, String> cardsToValues;

    /**
     * The View that displays the user's stats.
     */
    TextView statDisplay;

    /**
     * The button that allows the user to advance to the last level.
     */
    Button nextLevel;

    /**
     * Set content view to this activity. Randomly assign values to this activity's cards, and
     * store the resulting assignments in cardsToValues. Set the initial appearances of this
     * activity's View objects.
     *
     * @param savedInstanceState the saved instance state of the activity, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_game);

        // Randomly assign values to cards.
        final List<String> cardValues = new ArrayList<String>() {{
            add("A");
            add("A");
            add("B");
            add("B");
            add("C");
            add("C");
        }};
        Collections.shuffle(cardValues);

        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final Button button4 = findViewById(R.id.button4);
        final Button button5 = findViewById(R.id.button5);
        final Button button6 = findViewById(R.id.button6);
        nextLevel = findViewById(R.id.nextLevel);

        cardsToValues = new HashMap<Button, String>() {{
            put(button1, cardValues.get(0));
            put(button2, cardValues.get(1));
            put(button3, cardValues.get(2));
            put(button4, cardValues.get(3));
            put(button5, cardValues.get(4));
            put(button6, cardValues.get(5));
        }};

        // Set initial appearances of this activity's View objects.
        statDisplay = findViewById(R.id.statDisplay);
        String statDisplayText = TURNSTAKEN + 0;
        statDisplay.setText(statDisplayText);

        for (Button card : cardsToValues.keySet()) {
            card.setOnClickListener(this);
            card.setText(BACKOFCARD);
            card.setBackgroundColor(Color.LTGRAY);
        }

        nextLevel.setOnClickListener(this);

        manager = new MatchingGameManager(this.cardsToValues.size());
    }

    /**
     * Record that a card was clicked in  manager. Check whether there are matches left to be made,
     * and set statDisplay text accordingly.
     *
     * @param view the card that was clicked.
     */
    @Override
    public void onClick(View view) {
        Button button = (Button) view;

        if (button.getText().equals(BACKOFCARD)) {
            manager.recordClick(button, cardsToValues);

            int matchesToBeMade = manager.getMatchesToBeMade();

            if (matchesToBeMade == 0) {
                double score = manager.getScore();
                String statDisplayText = SCORE + score;
                this.statDisplay.setText(statDisplayText);
                nextLevel.setVisibility(View.VISIBLE);
            } else {
                int turnsTaken = manager.getTurnsTaken();
                String statDisplayText = TURNSTAKEN + turnsTaken;
                this.statDisplay.setText(statDisplayText);
            }
        }
        else if (button.getText().equals(NEXT)){
            startActivity(new Intent(MatchingGameActivity.this, MazeActivity.class));
        }

    }
}
