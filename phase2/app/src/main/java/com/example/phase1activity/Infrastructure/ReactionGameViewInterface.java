package com.example.phase1activity.Infrastructure;

/**
 * Interface for ReactionGameView.
 */
public interface ReactionGameViewInterface {

    /**
     * Update text displayed in the middle of the screen to guide user.
     * @param toThis text to display.
     * @param color colour to display toThis in.
     */
    void updateGameStateView(String toThis, int color);

    /**
     * Update user's current score on the screen.
     * @param toThisScore int representing user's current score.
     */
    void updateScoreView(int toThisScore);

    /**
     * End current activity and launch MatchingGame.
     */
    void endActivity();

    /**
     * Access AppManager and let it know of the user's statistics.
     * @param reactionTime fastest reaction time of user.
     * @param moves number of moves user took (i.e. the number of times user pressed the button to
     *              react).
     * @param score user's score.
     */
    void updateProfileStatistics(double reactionTime, int moves, int score);
}
