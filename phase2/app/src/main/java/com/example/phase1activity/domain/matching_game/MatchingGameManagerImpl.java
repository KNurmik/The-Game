package com.example.phase1activity.domain.matching_game;

import android.widget.Button;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/** A class for managing backend processes of a matching game. */
public class MatchingGameManagerImpl implements MatchingGameManager {

  /** The number of turns taken. */
  private int turnsTaken = 0;

  /** The number of matches needed to be made to complete the level. */
  private int matchesToBeMade;

  /** The number of cards that the level begins with. */
  private int numCards;

  /**
   * Cards clicked in this MatchingGameManagerImpl's respective MatchingGameActivity that have yet
   * to be checked for a potential match.
   */
  private Button[] cardsClicked = new Button[2];

  /**
   * * Initialize a new MatchingGameManagerImpl with numCards amount of cards.
   *
   * @param numCards the number of cards in this MatchingGameManagerImpl's respective
   *     MatchingGameActivity.
   */
  @Inject
  public MatchingGameManagerImpl(int numCards) {
    this.matchesToBeMade = numCards / 2;
    this.numCards = numCards;
  }

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
  public boolean recordClick(Button card, Map<Button, String> cardValues) {
    if (this.cardsClicked[0] == null) {
      this.cardsClicked[0] = card;
      return false;
    } else {
      this.cardsClicked[1] = card;
      this.takeTurn(cardsClicked, cardValues);
      return true;
    }
  }

  /**
   * If the values of the cards in cards match, decrement matches to be made. Clear cardsClicked.
   *
   * @param cards the cards in the game
   * @param cardValues the values of the cards
   */
  private void takeTurn(Button[] cards, Map<Button, String> cardValues) {
    this.turnsTaken++;

    if (cardValues.get(cards[0]).equals(cardValues.get(cards[1]))) {
      this.matchesToBeMade--;
    }
    this.cardsClicked[0] = null;
    this.cardsClicked[1] = null;
  }

  /**
   * Returns number of turns taken.
   *
   * @return the number of turns taken.
   */
  public int getTurnsTaken() {
    return this.turnsTaken;
  }

  /**
   * Returns number of matches left to be made.
   *
   * @return the number of matches left to be made.
   */
  public int getMatchesToBeMade() {
    return this.matchesToBeMade;
  }

  /**
   * Returns the score.
   *
   * @return the score.
   */
  public int getScore() {
    return Math.max(1000 * numCards - 500 * turnsTaken, 0);
  }
}
