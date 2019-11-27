package com.example.phase1activity.Core.Logic.MatchingGame;

import android.widget.Button;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.view.View;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.UI.MatchingGame.MatchingGameActivity;

import javax.inject.Inject;

import dagger.Provides;

/**
 * A MatchingGameManager that keeps track of information for some respective MatchingGameActivity.
 */
public class MatchingGameManager {

  /** The number of turns taken. */
  private int turnsTaken = 0;

  /** The number of matches needed to be made until the matching game's level is complete. */
  private int matchesToBeMade;

  /** The number of cards that the level begins with.*/
  private int numCards;

  /**
   * Cards clicked in this MatchingGameManager's respective MatchingGameActivity that have yet to be
   * checked for a potential match.
   */
  private Button[] cardsClicked = new Button[2];

  /**
   * * Initialize a new MatchingGameManager with numCards amount of cards.
   *
   * <p>Precondition: numCards is divisible by 2.
   *
   * @param numCards the number of cards in this MatchingGameManager's respective
   *     MatchingGameActivity.
   */
  @Inject
  public MatchingGameManager(int numCards) {
    this.matchesToBeMade = numCards / 2;
    this.numCards = numCards;
  }

  /**
   * * Record that card was clicked. That is, if cardsClicked is empty, add card to cardsClicked. If
   * there is one card in cardsClicked, add card to cardsClicked, and call takeTurn with
   * cardsClicked. Return true if takeTurn was called, and false otherwise.
   *
   * <p> Tell MatchingGameModule how to create this object.
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
   * Return 0 if cards do not match. Return 1 otherwise. If there are no more
   * matches to be made, end the game, and display the user's score.
   *
   * @param cards the cards in the game
   * @param cardValues the values of the cards
   */
  private void takeTurn(Button[] cards, Map<Button, String> cardValues) {
    this.turnsTaken++;

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
    } // TODO: replace empty catch block with a log

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
   * Returns number of turns that have been made.
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
