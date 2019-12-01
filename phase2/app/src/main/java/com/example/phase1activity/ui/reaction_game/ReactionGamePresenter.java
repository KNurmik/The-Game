package com.example.phase1activity.ui.reaction_game;

/** Interface for ReactionGamePresenterImpl. Responsible for handling user input. */
public interface ReactionGamePresenter {

  /** Called by ReactionGameViewImpl when user presses the button (in the middle of the screen). */
  void handleClick();
}
