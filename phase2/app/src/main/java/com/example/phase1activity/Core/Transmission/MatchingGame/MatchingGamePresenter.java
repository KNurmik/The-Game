package com.example.phase1activity.Core.Transmission.MatchingGame;

import android.widget.Button;

import com.example.phase1activity.Core.Logic.MatchingGame.MatchingGameManager;
import com.example.phase1activity.Core.Logic.MatchingGame.MatchingGameManagerImpl;
import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.UI.MatchingGame.MatchingGameActivityInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/** A presenter for some matching game, that relays information to and from its view and manager. */
public class MatchingGamePresenter implements MatchingGamePresenterInterface {
  /** The string to be displayed on the back of each card. */
  private final String BACKOFCARD = "CLICK ME!";

  /** The string to be displayed next to the final score. */
  private final String SCORE = "Final Score: ";

  /** A map of cards to their respective values. */
  private Map<Button, String> cardsToValues = new HashMap<>();

  /** This MatchingGameActivity's MatchingGameManagerImpl */
  private MatchingGameManager manager;

  /** The string to be displayed next to the number of turns taken. */
  private final String TURNSTAKEN = "Turns Taken: ";

  /** This presenter's view. */
  private MatchingGameActivityInterface view;

  /** The card's clicked by the user to be given to the manager. */
  private List<Button> cardsClicked = new ArrayList<>();

  /** Initialize this MatchingGamePresenter */
  @Inject
  public MatchingGamePresenter(List<Button> buttonList, MatchingGameActivityInterface view, int numCards) {

    assignCardValues(buttonList, numCards);
    manager = new MatchingGameManagerImpl(this.cardsToValues.size());
    this.view = view;
  }

  /**
   * Populate cardsToValues with randomized values for each button in buttonList.
   *
   * @param buttonList a list of the buttons that represent cards.
   */
  private void assignCardValues(List<Button> buttonList, int numCards) {
    final List<String> cardValues =
        new ArrayList<String>() {
          {
            add("triangle");
            add("triangle");
            add("square");
            add("square");
            add("line");
            add("line");
            add("wave");
            add("wave");
            add("circle");
            add("circle");
            add("equal");
            add("equal");
          }
        };

    List<String> valuesNeeded = cardValues.subList(0, numCards);
    Collections.shuffle(valuesNeeded);

    // Assign all cards a value.
    for (int i = 0; i < numCards; i++) {
      cardsToValues.put(buttonList.get(i), valuesNeeded.get(i));
    }
  }

  /**
   * If button is a card, record the click. If there are no matches left to be made,
   * display the final score, and update the user's statistics.
   *
   * @param button the button that was clicked.
   * @param app the AppManager.
   */
  public void handleClick(Button button, AppManager app) {
    if (button.getText().equals(BACKOFCARD)) {
      button.setText("");
      view.setButtonImage(button, cardsToValues);
      cardsClicked.add(button);

      int matchesToBeMadeBefore = manager.getMatchesToBeMade();
      boolean turnTaken = manager.recordClick(button, cardsToValues);
      int matchesToBeMadeAfter = manager.getMatchesToBeMade();
      int matchesMadeThisTurn = matchesToBeMadeBefore - matchesToBeMadeAfter;

      // A turn was taken.
      if (turnTaken) {
        if (matchesMadeThisTurn == 0) {
          view.showNoMatchPopup();
          view.flipFaceUpButtons();
        } else {
          view.hideFaceUpButtons(cardsClicked);
        }
        cardsClicked.clear();
      }

      if (matchesToBeMadeAfter == 0) {
        double score = manager.getScore();
        String statDisplayText = SCORE + score;
        view.setDisplayStat(statDisplayText);
        view.updateNextLevelButton();
        view.updateProfileStats(manager.getScore(), manager.getTurnsTaken());
      } else {
        int turnsTaken = manager.getTurnsTaken();
        String statDisplayText = TURNSTAKEN + turnsTaken;
        view.setDisplayStat(statDisplayText);
      }
    }
  }

  /** @param manager the MatchingGameManagerImpl to set manager to. */
  public void setManager(MatchingGameManagerImpl manager) {
    this.manager = manager;
  }
}
