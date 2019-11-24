package com.example.phase1activity.UI.MatchingGame;

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
}
