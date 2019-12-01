package com.example.phase1activity.ui.maze_game;

public interface MazeGameView {

  /** Updates the profile statistics if the user finished the game */
  void updateProfileStatistics();

  /** @return the drawView instances of this View. */
  MazeGameViewImpl.DrawView getView();

  /** The maze is finished then move onto the next screen. */
  void finishMaze();
}
