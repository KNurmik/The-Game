/*
 * Copyright 2019. Mark Vartolaş, Karl Hendrik Nurmeots, Olivia Li, Ramzi Dajani, Henry Leung.
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

package com.example.phase1activity.Core.Logic.ReactionGame;

import org.springframework.util.StopWatch;

import java.util.Random;

import javax.inject.Inject;

import static java.lang.Math.min;

/** Class for running backend processes for ReactionGame. */
public class ReactionGameManager {
  /** The user's score. */
  private double score = 0;
  /** The user's fastest reaction in milliseconds. */
  private double fastestReaction;
  /** During the spamming part of the game, this shows how many clicks the user has left to do. */
  private int timesToClickLeft;
  /**
   * Similar to timesToClickLeft, this shows the total number of clicks user has to do during this
   * run of spamming.
   */
  private int timesToClickTotal;
  /** StopWatch object for timing user reaction time. */
  private StopWatch timer = new StopWatch();
  /** Enum for keeping track of the state the game is in. */
  private State gameState = State.BEGINNING;
  /** How much time user has in the "bank" in milliseconds. */
  private double timeLimit;

  /**
   * Initialize ReactionGameManager. Tell Dagger how to construct this object.
   *
   * @param difficulty represents the difficulty desired. Potential Phase 2 extension adds more
   *     difficulty settings.
   */
  @Inject
  public ReactionGameManager(String difficulty) {
    if (difficulty.equals("easy")) {
      timeLimit = 8000;
      // Fastest reaction time is the entire duration of the game in case the user
      // simply lets the game run.
      fastestReaction = timeLimit;
    }
  }

  /** @return if user has time left in timeLimit. */
  public boolean isTimeLeft() {
    return timeLimit > 0;
  }

  /** @return the state the game is in. */
  public State getGameState() {
    return gameState;
  }

  /** @param state the state to set gameState to. */
  public void setGameState(State state) {
    gameState = state;
  }

  /** @return the user's score. */
  public int getScore() {
    return (int) score;
  }

  /**
   * Deal with user pressing the button depending on the game's state. If the user pressed the
   * button too early, deduct 1 second from timeLimit. If the user reacted after being prompted to
   * do so, stop the timer and update the score depending on how long it took them to react.
   *
   * <p>If user has to spam the button, either accept the click, or if user has pressed the button
   * enough times, reward them if they were quick enough.
   */
  public void press() {
    // User pressed button too early, reduce timeLimit.
    if (gameState.equals(State.DONTREACT)) {
      // penaltyTime += 1;
      timeLimit -= 1000;
    }

    // User reacted correctly.
    else if (gameState.equals(State.REACT)) {
      timer.stop();
      double startTime = timeLimit;
      // timeLimit = 5 - penaltyTime - timer.getTotalTimeSeconds();
      timeLimit -= timer.getLastTaskTimeMillis();

      // Score is calculated 100 times 1 / time it took to react.
      score += 100 * (1 / ((startTime - timeLimit) / 1000));

      if (startTime - timeLimit > 0) {
        fastestReaction = min(fastestReaction, startTime - timeLimit);
      }

    }
    // User has to spam button.
    else if (gameState.equals(State.SPAMBUTTON)) {
      if (timesToClickLeft > 0) {
        timesToClickLeft -= 1;
        score += 50;
      } else {
        timer.stop();
        double timeSpent = timer.getLastTaskTimeMillis();
        // timeLimit += timeSpent;
        // If avg. time per click is less than 0.2s, reward player.
        if (timeSpent / timesToClickTotal < 0.3) {
          timeLimit += 1000;
          score += 1000;
        }
        setGameState(State.BEGINNING);
      }
    }
  }

  /** @return the fastest reaction in seconds for user, rounded to 2 decimal places. */
  public double getFastestReaction() {
    return Math.floor(fastestReaction * 100) / 100;
  }

  /** Start the timer. */
  void startTimer() {
    timer.start("time");
  }

  /** @return if the timer is running. */
  boolean timeRunning() {
    return timer.isRunning();
  }

  /** The most regular reacting feature of the game. Update gameState and start the timer. */
  public void playSimpleReaction() {
    setGameState(State.REACT);
    startTimer();
  }

  /**
   * User has to spam the button a randomized amount of times. Update gameState and start the timer.
   */
  public void playSpamButton() {
    setGameState(State.SPAMBUTTON);
    Random r = new Random();
    timesToClickLeft = r.nextInt((50 - 15) + 1) + 15;
    timesToClickTotal = timesToClickLeft;
    startTimer();
  }

  /** @return the number of times user has to spam the button. */
  public int getTimesToClickLeft() {
    return timesToClickLeft;
  }

  /** @return the time user has left in the bank. */
  public double getTimeLeft() {
    return timeLimit;
  }

  /** Enums for the state the game is in. */
  public enum State {
    BEGINNING,
    REACT,
    DONTREACT,
    GAMEOVER,
    SPAMBUTTON
  }
}
