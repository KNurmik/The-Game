package com.example.phase1activity.domain.maze_game;

import java.util.List;

public interface MazeManager {
  /** @return the mazeObject for this maze manager. */
  Maze getMazeObject();

  /**
   * Draw every outer wall of the maze as well as any walls that are neighbours of neighbours of
   * the currentBlock that the player is on, as well as the player.
   *
   * @param canvas the canvas.
   */
  // void draw(Canvas canvas);

  /**
   * Return true if and only if the character is on the winning block and the user has one the game.
   *
   * @return Whether the player is currently standing on the winning block or not.
   */
  boolean checkWin();

  /**
   * If the player obtains the coin, increase the points of the user and remove the coin from the
   * maze.
   */
  void gotCoin();

  /**
   * If the player steps onto a teleporting maze block, move the user to the other teleporting maze
   * block and remove the two teleporting maze blocks from the maze.
   */
  void teleport();

  /**
   * Calculates the score of the player and returns it
   *
   * @return The updated score of the player after they move
   */
  int calculateScore();

  /**
   * Get the x coordinate of the player
   *
   * @return the x coordinate of the player
   */
  int getPlayerX();

  /**
   * Get the y coordinate of the player
   *
   * @return the y coordinate of the player
   */
  int getPlayerY();

  /**
   * Move the player according to the direction d given.
   *
   * @param d the direction that the user wants to move the maze character.
   */
  void movePlayer(Character.Direction d);

  /** @return the character instances from this classes maze instance. */
  Character getMazePlayer();

  /** @return the coin instance from this classes maze instance. */
  Coin getCoin();

  /** @return the first teleport maze block instance from this classes maze instance. */
  MazeBlock getTeleportBlock1();

  /** @return the second teleport maze block instance from this classes maze instance. */
  MazeBlock getTeleportBlock2();

  /** @return the list of maze walls from this classes maze instance. */
  List<Wall> getMazeWalls();

  /** @return the list of outer maze walls from this classes maze instance. */
  List<Wall> getOuterWalls();
}
