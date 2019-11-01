package com.example.phase1activity.Levels.ReactionGame;

import android.os.CountDownTimer;

import org.springframework.util.StopWatch;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Class for running backend processes for ReactionGame.
 */
public class ReactionGameManager {
    private double score = 0;
    /**
     * StopWatch object for timing user reaction time.
     */
    private StopWatch timer = new StopWatch();
    /**
     * String object for keeping track of the state the game is in.
     */
    private String gameState = "beginning";
    /**
     * How much time user has in the "bank".
     */
    private double timeLimit;

    /**
     * Initialize ReactionGameManager.
     *
     * @param difficulty represents the difficulty desired. Potential Phase 2 extension adds more
     *                   difficulty settings.
     */
    ReactionGameManager(String difficulty) {
        if (difficulty.equals("easy")) {
            timeLimit = 5;
        }
    }

    /**
     * @return if user has time left in timeLimit.
     */
    boolean isTimeLeft() {
        return timeLimit > 0;
    }

    /**
     * @return the state the game is in.
     */
    String getGameState() {
        return gameState;
    }

    /**
     * @param state the state to set gameState to.
     */
    void setGameState(String state) {
        gameState = state;
    }

    /**
     * @return the user's score.
     */
    String getScore() {
        int scoreInt = (int) score;
        return Integer.toString(scoreInt);
    }

    /**
     * Deal with user pressing the button depending on the game's state. If the user pressed the
     * button too early, deduct 1 second from timeLimit. If the user reacted after being prompted
     * to do so, stop the timer and update the score depending on how long it took them to react.
     */
    void press() {
        // User pressed button too early, reduce timeLimit.
        if (gameState.equals("do not react")) {
            timeLimit -= 1;
        }

        // User reacted correctly.
        else if (gameState.equals("react")) {
            timer.stop();
            double startTime = timeLimit;
            timeLimit = 5 - timer.getTotalTimeSeconds();

            // Score is calculated 100 times 1 / time it took to react.
            score += 100 * (1 / (startTime - timeLimit));
        }
    }

    /**
     * Start the timer.
     */
    void startTimer() {
        timer.start("time");
    }

    /**
     * @return if the timer is running.
     */
    boolean timeRunning() {
        return timer.isRunning();
    }

    /**
     * Update gameState and start the timer.
     */
    void play() {
        setGameState("react");
        startTimer();

    }


}

