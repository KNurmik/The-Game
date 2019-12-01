package com.example.phase1activity.ui.matching_game;

import android.widget.Button;

import java.util.List;
import java.util.Map;

/** View interface for displaying MatchingGame. */
public interface MatchingGameView {
  /**
   * Set the statistic to be displayed to statDisplayText.
   *
   * @param statDisplayText a statistic.
   */
  void setDisplayStat(String statDisplayText);

  /** Hide the button that takes the user to the next level. */
  void updateNextLevelButton();

  /** Display popup that user did not get a matching pair. */
  void showNoMatchPopup();

  /** Hide the popup. */
  void hideNoMatchPopup();

  /**
   * Hide the inputted buttons.
   *
   * @param buttons buttons that are faced up.
   */
  void hideFaceUpButtons(List<Button> buttons);

  /** Flip all of the buttons that are faced up. */
  void flipFaceUpButtons();

  /**
   * Assign an image to the inputted button.
   *
   * @param button the button in question.
   * @param cardsToValues a map of cards to values.
   */
  void setButtonImage(Button button, Map<Button, String> cardsToValues);

  /** Update profile statistics. */
  void updateProfileStats(int score, int moves);
}
