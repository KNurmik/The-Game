package com.example.phase1activity.Levels.ReactionGame;

import android.os.CountDownTimer;

import org.springframework.util.StopWatch;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ReactionGameManager {
    private double score = 0;
    private StopWatch timer = new StopWatch();
    private String gameState = "beginning";
    private double timeLimit;

    ReactionGameManager(String difficulty){
        if(difficulty.equals("easy")){
            timeLimit = 5;
        }
    }

    boolean isTimeLeft(){return timeLimit > 0;}

    String getGameState(){return gameState;}
    void setGameState(String state){gameState = state;}

    String getScore(){
        int scoreInt = (int) score;
        return Integer.toString(scoreInt);
    }

    void press(){
        // User pressed button too early, reduce timeLimit.
        if(gameState.equals("do not react")){
            timeLimit -= 1;
        }

        // User reacted correctly.
        else if(gameState.equals("react")){
            timer.stop();
            double startTime = timeLimit;
            timeLimit = 5 - timer.getTotalTimeSeconds();

            // Score is calculated 100 times 1 / time it took to react.
            score += 100 * (1/(startTime - timeLimit));
        }
    }

    void startTimer(){
        timer.start("time");
    }

    boolean timeRunning(){return timer.isRunning();}

    void play(){
        // Wait a random amount of time no longer than 5 seconds.

        setGameState("react");
        startTimer();

    }




}

