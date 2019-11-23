package com.example.phase1activity.Core.Transmission.ReactionGame;

/**
 * Interface for ReactionGamePresenter.
 */
public interface ReactionGamePresenterInterface {

    /**
     * Called by ReactionGameView when user presses the button (in the middle of the screen). Calls
     * appropriate methods based on the state of the game.
     */
    void handleClick();
}
