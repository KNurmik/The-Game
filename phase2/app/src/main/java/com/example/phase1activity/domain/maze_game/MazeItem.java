package com.example.phase1activity.domain.maze_game;

/** A MazeItem in a Maze. */
public class MazeItem {

  /** The MazeItem's x coordinate. */
  private int x;

  /** The MazeItem's y coordinate. */
  private int y;

  /**
   * Construct a MazeItem.
   *
   * @param a The initial x value of the MazeItem.
   * @param b The initial y value of the MazeItem.
   */
  MazeItem(int a, int b) {
    x = a;
    y = b;
  }

  /**
   * Getter for MazeItem.x
   *
   * @return x
   */
  public int getX() {
    return x;
  }

  /**
   * Getter for MazeItem.y
   *
   * @return y
   */
  public int getY() {
    return y;
  }
}
