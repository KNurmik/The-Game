package com.example.phase1activity.Core.Transmission.MazeGame;

import android.view.MotionEvent;

/**
 * Interface for MazeGamePresenter.
 */
public interface MazeGamePresenterInterface {

    /**
     * Called by MazeGameView when the player touches the screen in an attempt to move the maze
     * character. It moves the character according to the direction and magnitude of the user's
     * movement on the screen.
     *
     * @param event the event of the player moving the maze character
     */
    boolean onTouchEvent(MotionEvent event);
}
