package com.example.phase1activity.Core.Transmission.MatchingGame;

import android.widget.Button;

import com.example.phase1activity.Core.Logic.MatchingGame.MatchingGameManager;
import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.UI.MatchingGame.MatchingGameActivityInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class MatchingGamePresenter implements MatchingGamePresenterInterface {
  /** The string to be displayed on the back of each card. */
  private final String BACKOFCARD = "CLICK ME!";

  /** The string to be displayed next to the final score. */
  private final String SCORE = "Final Score: ";

  /** A map of cards to their respective values. */
  private Map<Button, String> cardsToValues = new HashMap<>();

  /** This MatchingGameActivity's MatchingGameManager */
  private MatchingGameManager manager;

  /** The string to be displayed next to the number of turns taken. */
  private final String TURNSTAKEN = "Turns Taken: ";

  private MatchingGameActivityInterface view;

  @Inject
  public MatchingGamePresenter(List<Button> buttonList, MatchingGameActivityInterface view) {
    assignCardValues(buttonList);
    manager = new MatchingGameManager(this.cardsToValues.size());
    this.view = view;
  }

  /**
   * Populate cardsToValues with randomized values ("A", "B", or "C") for each button in buttonList.
   *
   * @param buttonList a list of the buttons that represent cards.
   */
  private void assignCardValues(List<Button> buttonList) {
    final List<String> cardValues =
        new ArrayList<String>() {
          {
            add("A");
            add("A");
            add("B");
            add("B");
            add("C");
            add("C");
          }
        };
    Collections.shuffle(cardValues);

    for (int i = 0; i <= 5; i++) {
      cardsToValues.put(buttonList.get(i), cardValues.get(i));
    }
  }

  /**
   * If button is a card, record the click. Subsequently, if there are no matches left to be made,
   * display the final score, and update the user's statistics.
   *
   * @param button the button that was clicked.
   * @param app the AppManager.
   */
  public void handleClick(Button button, AppManager app) {
    if (button.getText().equals(BACKOFCARD)) {
      int matchesToBeMadeBefore = manager.getMatchesToBeMade();
      boolean turnTaken = manager.recordClick(button, cardsToValues);
      int matchesToBeMadeAfter = manager.getMatchesToBeMade();

      // The user failed to match a pair.
      if (matchesToBeMadeAfter == matchesToBeMadeBefore && turnTaken) {
        view.showNoMatchPopup();
      }

      // The user has matched all cards.
      if (matchesToBeMadeAfter == 0) {
        double score = manager.getScore();
        String statDisplayText = SCORE + score;
        view.setDisplayStat(statDisplayText);
        view.updateNextLevelButton();

        app.updateProfileScore(manager.getScore());
        app.updateProfileMoves(manager.getTurnsTaken());
      }
      // The user still has matches to make.
      else {
        int turnsTaken = manager.getTurnsTaken();
        String statDisplayText = TURNSTAKEN + turnsTaken;
        view.setDisplayStat(statDisplayText);
      }
    }
  }

  /** @param manager the MatchingGameManager to set manager to. */
  public void setManager(MatchingGameManager manager) {
    this.manager = manager;
  }
}
