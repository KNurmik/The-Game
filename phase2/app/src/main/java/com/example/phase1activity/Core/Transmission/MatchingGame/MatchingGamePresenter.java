package com.example.phase1activity.Core.Transmission.MatchingGame;

import android.view.View;
import android.widget.Button;

import com.example.phase1activity.Core.Logic.MatchingGame.MatchingGameManager;
import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.UI.MatchingGame.MatchingGameActivity;
import com.example.phase1activity.UI.MatchingGame.MatchingGameActivityInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingGamePresenter implements MatchingGamePresenterInterface {
  /** The string to be displayed on the back of each card. */
  private static final String BACKOFCARD = "CLICK ME!";

  /** The string to be displayed next to the final score. */
  static final String SCORE = "Final Score: ";

  /** A map of cards to their respective values. */
  private Map<Button, String> cardsToValues = new HashMap<>();

  /** This MatchingGameActivity's MatchingGameManager */
  private MatchingGameManager manager;

  /** The string to be displayed next to the number of turns taken. */
  static final String TURNSTAKEN = "Turns Taken: ";

  private MatchingGameActivityInterface view;

  public MatchingGamePresenter(List<Button> buttonList, MatchingGameActivityInterface view) {
    assignCardValues(buttonList);
    manager = new MatchingGameManager(this.cardsToValues.size());
    this.view = view;
  }

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

  public void handleClick(Button button, AppManager app) {
    if (button.getText().equals(BACKOFCARD)) {
      manager.recordClick(button, cardsToValues);

      int matchesToBeMade = manager.getMatchesToBeMade();

      // The user successfully matches all cards
      if (matchesToBeMade == 0) {
        double score = manager.getScore();
        String statDisplayText = SCORE + score;
        view.setDisplayStat(statDisplayText);
        view.setFinishMatchesVisibility();

        app.updateProfileScore(manager.getScore());
        app.updateProfileMoves(manager.getTurnsTaken());
      }
      // The user still has matches to make
      else {
        int turnsTaken = manager.getTurnsTaken();
        String statDisplayText = TURNSTAKEN + turnsTaken;
        view.setDisplayStat(statDisplayText);
      }
    }
  }
}
