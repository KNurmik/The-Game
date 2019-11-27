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
