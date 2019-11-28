package com.example.phase1activity.Core.Logic.MazeGame;

import android.graphics.Canvas;
import javax.inject.Inject;

public class MazeManager {

  /** The maze that will be displayed on the phone */
  public Maze mazeObject;

  /** Constructs the MazeManager */
  @Inject
  public MazeManager(Maze maze) {
    this.mazeObject = maze;
    mazeObject.createPlayer();
  }

  /**
   * Draws every Wall in the maze, as well as the player
   *
   * @param canvas The canvas that the phone will use
   */
  public void draw(Canvas canvas) {
    for (Wall wall : mazeObject.mazeWalls) {
      wall.draw(canvas);
    }
    mazeObject.player.draw(canvas);
  }
}
