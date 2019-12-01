package com.example.phase1activity.ui.matching_game;

import android.widget.Button;

import com.example.phase1activity.service.AppManager;

/** A presenter for the matching game, relays information to and from its view and manager. */
public interface MatchingGamePresenter {
  /**
   * If button is a card, record the click. Subsequently, if there are no matches left to be made,
   * display the final score, and update the user's statistics.
   *
   * @param button the button that was clicked.
   * @param app the AppManager.
   */
  void handleClick(Button button, AppManager app);
}
