package com.example.phase1activity.Core.Transmission.MatchingGame;

import android.widget.Button;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;

// TODO: insert description.
public interface MatchingGamePresenterInterface {
  /**
   * If button is a card, record the click. Subsequently, if there are no matches left to be made,
   * display the final score, and update the user's statistics.
   *
   * @param button the button that was clicked.
   * @param app the AppManager.
   */
  void handleClick(Button button, AppManager app);
}
