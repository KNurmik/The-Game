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
package com.example.phase1activity.UI.ReactionGame;

import android.content.res.ColorStateList;

import androidx.annotation.DrawableRes;

/** Interface for ReactionGameView. */
public interface ReactionGameViewInterface {

  /**
   * Update text displayed in the middle of the screen to guide user.
   *
   * @param newState The image resource displayed for the instructions
   */
  void updateGameStateView(@DrawableRes int newState);

  // TODO: REMOVE THIS TEST METHOD!!!!!!!!!!!!
  void updateTestGameStateView(String toThis, int colour);

  void updateTimeLeft(String toThis);

  /**
   * Update user's current score on the screen.
   *
   * @param toThisScore int representing user's current score.
   */
  void updateScoreView(int toThisScore);

  /** End current activity and launch MatchingGame. */
  void endActivity();

  /**
   * Access AppManager and let it know of the user's statistics.
   *
   * @param reactionTime fastest reaction time of user.
   * @param moves number of moves user took (i.e. the number of times user pressed the button to
   *     react).
   * @param score user's score.
   */
  void updateProfileStatistics(double reactionTime, int moves, int score);

  /**
   * Getter for defaultColour.
   *
   * @return defaultColour of type ColorStateList.
   */
  ColorStateList getColorStateList();
}
