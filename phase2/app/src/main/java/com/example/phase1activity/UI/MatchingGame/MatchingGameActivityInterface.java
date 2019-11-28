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
package com.example.phase1activity.UI.MatchingGame;

import android.widget.Button;

import java.util.List;
import java.util.Map;

public interface MatchingGameActivityInterface {
  /**
   * Set the statistic to be displayed to statDisplayText.
   *
   * @param statDisplayText a statistic.
   */
  void setDisplayStat(String statDisplayText);

  /** Hide the button that takes the user to the next level. */
  void updateNextLevelButton();

  /** Hide
   *
   */
  void showNoMatchPopup();

  /** Hide
   *
   */
  void hideNoMatchPopup();

  void hideFaceUpButtons(List<Button> buttons);

  void flipFaceUpButtons();

  void setButtonImage(Button button, Map<Button, String> cardsToValues);
}
