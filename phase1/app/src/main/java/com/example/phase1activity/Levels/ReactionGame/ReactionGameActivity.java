package com.example.phase1activity.Levels.ReactionGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase1activity.MatchingGameActivity;
import com.example.phase1activity.R;
import com.example.phase1activity.presentation.MainMenu.StartActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ReactionGameActivity extends AppCompatActivity implements View.OnClickListener {
    ReactionGameManager manager;
    Button btn;
    ColorStateList defaultColor;
    Button nextbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game);

        manager = new ReactionGameManager("easy");
        nextbtn = findViewById(R.id.Next);
        btn = findViewById(R.id.reactButton);

        TextView textView = findViewById(R.id.gameStateView);
        defaultColor = textView.getTextColors();


        updateGameStateView("Press the button to start the game.", defaultColor.getDefaultColor());
        updateScoreView("Your score is: 0");

        nextbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReactionGameActivity.this, MatchingGameActivity.class));
            }
        });

    }

    @Override
    public void onClick(View v) {

        // Turn hasn't stated, begin the game.
        if(manager.getGameState().equals("beginning")){
            beginGame();
        }
        // Game has begun, user pressed button too early.
        else if(manager.getGameState().equals("do not react")){
            manager.press();
            updateGameStateView("Too soon! Don't press the button!!", Color.RED);
        }
        // User reacted correctly.
        else if (manager.getGameState().equals("react")){
            manager.press();
            updateGameStateView("Well done!", Color.BLUE);
            updateScoreView("Your score is: " + manager.getScore());
            manager.setGameState("beginning");
        }

        // Game is over.
        else{

        }

    }

    public void updateGameStateView(String toThis, int color) {
        TextView textView = (TextView) findViewById(R.id.gameStateView);
        textView.setText(toThis);
        textView.setTextColor(color);
    }

    public void updateScoreView(String toThis){
        TextView textView = (TextView) findViewById(R.id.scoreView);
        textView.setText(toThis);
    }

    void beginGame(){
        if(manager.isTimeLeft()){

            manager.setGameState("do not react");
            updateGameStateView("Don't press the button.", Color.MAGENTA);

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

        else{
            updateGameStateView("GAME OVER", defaultColor.getDefaultColor());
            manager.setGameState("game over");
            updateScoreView("Your score is: " + manager.getScore());
        }
    }


}
