package com.example.phase1activity.domain.matching_game;

import android.widget.Button;

import java.util.Map;

/** A class for managing backend processes of a matching game. */
public interface MatchingGameManager {
  /**
   * * Record that card was clicked. That is, if cardsClicked is empty, add card to cardsClicked. If
   * there is one card in cardsClicked, add card to cardsClicked, call takeTurn, and clear
   * cardsClicked. Return true if takeTurn was called, and false otherwise.
   *
   * <p>Tell MatchingGameModule how to create this object.
   *
   * @param card the card clicked.
   * @param cardValues a map of cards to their respective values.
   */
  boolean recordClick(Button card, Map<Button, String> cardValues);

  /**
   * Returns number of matches left to be made.
   *
   * @return the number of matches left to be made.
   */
  int getMatchesToBeMade();

  /**
   * Returns the score.
   *
   * @return the score.
   */
  int getScore();

  /**
   * Returns number of turns taken.
   *
   * @return the number of turns taken.
   */
  int getTurnsTaken();
}
