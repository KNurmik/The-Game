/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// This license is necessary for the imported org.springframework.util.StopWatch package.

package com.example.phase1activity.Domain.ReactionGame;

import org.springframework.util.StopWatch;

import static java.lang.Math.min;


/**
 * Class for running backend processes for ReactionGame.
 */
public class ReactionGameManager {
    private double score = 0;
    private double fastestReaction = 5;
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

    private int penaltyTime;

    /**
     * Initialize ReactionGameManager.
     *
     * @param difficulty represents the difficulty desired. Potential Phase 2 extension adds more
     *                   difficulty settings.
     */
    public ReactionGameManager(String difficulty) {
        if (difficulty.equals("easy")) {
            timeLimit = 5;
        }
    }

    /**
     * @return if user has time left in timeLimit.
     */
    public boolean isTimeLeft() {
        return timeLimit > 0;
    }

    /**
     * @return the state the game is in.
     */
    public String getGameState() {
        return gameState;
    }

    /**
     * @param state the state to set gameState to.
     */
    public void setGameState(String state) {
        gameState = state;
    }

    /**
     * @return the user's score.
     */
    public int getScore() {
        return (int) score;
    }

    /**
     * Deal with user pressing the button depending on the game's state. If the user pressed the
     * button too early, deduct 1 second from timeLimit. If the user reacted after being prompted
     * to do so, stop the timer and update the score depending on how long it took them to react.
     */
    public void press() {
        // User pressed button too early, reduce timeLimit.
        if (gameState.equals("do not react")) {
            penaltyTime += 1;
        }

        // User reacted correctly.
        else if (gameState.equals("react")) {
            timer.stop();
            double startTime = timeLimit;
            timeLimit = 5 - penaltyTime - timer.getTotalTimeSeconds();

            // Score is calculated 100 times 1 / time it took to react.
            score += 100 * (1 / (startTime - timeLimit));

            if(startTime - timeLimit > 0) {
                fastestReaction = min(fastestReaction, startTime - timeLimit);
            }

        }
    }

    /**
     * @return the fastest reaction in seconds for user, rounded to 2 decimal places.
     */
    public double getFastestReaction(){return Math.floor(fastestReaction * 100) / 100;}

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
    public void play() {
        setGameState("react");
        startTimer();

    }


}

