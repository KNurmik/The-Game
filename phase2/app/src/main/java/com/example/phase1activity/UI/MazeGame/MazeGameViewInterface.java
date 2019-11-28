package com.example.phase1activity.UI.MazeGame;

public interface MazeGameViewInterface {
    boolean checkWin();
    void updateProfileStatistics();
    MazeGameActivity.DrawView getView();
    void finishMaze();
}
