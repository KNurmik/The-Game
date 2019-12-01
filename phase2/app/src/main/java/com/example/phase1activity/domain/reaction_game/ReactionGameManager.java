package com.example.phase1activity.domain.reaction_game;

public interface ReactionGameManager {

  /** @return the enum corresponding to the state the game is in. */
  ReactionGameManagerImpl.State getGameState();

  /** Handle user pressing the button. */
  void press();

  /** @return the time user has left in the bank, in milliseconds. */
  int getTimeLeft();

  /** @return the user's current score. */
  int getScore();

  /** @param state the state to set the game's state to. */
  void setGameState(ReactionGameManagerImpl.State state);

  /** @return if user has time left in the bank. */
  boolean isTimeLeft();

  /** Start minigame where user has to spam the button a randomized amount of times. */
  void playSpamButton();

  /** Start regular turn where user has to react to on-screen prompts. */
  void playSimpleReaction();

  /** @return the user's fastest reaction time during this playthrough. */
  int getFastestReaction();
}
