package com.example.phase1activity.Core.Logic.MatchingGame;

import android.widget.Button;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.view.View;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.UI.MatchingGame.MatchingGameActivity;

// TODO: make game extendable (add levels without replacing code, i.e open closed principle), clean
// code, srp

/**
 * A MatchingGameManager that keeps track of information for some respective MatchingGameActivity.
 */
public class MatchingGameManager {

  /** The number of turns taken. */
  private int turnsTaken = 0;

  /** The number of matches needed to be made until the matching game's level is complete. */
  private int matchesToBeMade;

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
  public MatchingGameManager(int numCards) {
    this.matchesToBeMade = numCards / 2;
  }

  /**
   * * Record that card was clicked. That is, if cardsClicked is empty, add card to cardsClicked. If
   * there is one card in cardsClicked, add card to cardsClicked, and call takeTurn with
   * cardsClicked.
   *
   * @param card the card clicked.
   * @param cardValues a map of cards to their respective values.
   */
  public void recordClick(Button card, Map<Button, String> cardValues) {
    card.setText(cardValues.get(card));

    if (this.cardsClicked[0] == null) {
      this.cardsClicked[0] = card;
    } else {
      this.cardsClicked[1] = card;
      this.takeTurn(cardsClicked, cardValues);
    }
  }

  /**
   * Flip the cards back over if they do not match. Hide them otherwise. If there are no more
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
    } // TODO: replace empty catch block with what?!

    if (cardValues.get(cards[0]).equals(cardValues.get(cards[1]))) {
      // Make cards disappear, store this info in this class somehow
      cards[0].setVisibility(View.INVISIBLE);
      cards[1].setVisibility(View.INVISIBLE);
      this.matchesToBeMade--;

    } else {
      // Flip cards back over, wait for another turn to be taken (does this work already?)
      cards[0].setText(MatchingGameActivity.BACKOFCARD);
      cards[1].setText(MatchingGameActivity.BACKOFCARD);
    }

    this.cardsClicked[0] = null;
    this.cardsClicked[1] = null;
  }

  /**
   * Get's number of turns taken
   *
   * @return the number of turns taken.
   */
  public int getTurnsTaken() {
    return this.turnsTaken;
  }

  /**
   * * Get's number of turns that have been made
   *
   * @return the number of matches left to be made.
   */
  public int getMatchesToBeMade() {
    return this.matchesToBeMade;
  }

  /**
   * * Gets the score after the game has finished
   *
   * @return the score.
   */
  public int getScore() {
    return Math.max(5000 - 500 * turnsTaken, 0);
  }
}
