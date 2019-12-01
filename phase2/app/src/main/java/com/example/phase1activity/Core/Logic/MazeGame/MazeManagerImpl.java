package com.example.phase1activity.Core.Logic.MazeGame;

import java.util.List;

import javax.inject.Inject;

public class MazeManagerImpl implements MazeManager {

  /** The maze to be displayed. */
  private Maze mazeObject;
  /** True iff the maze game is easy. */
  private boolean difficulty;

  /** Construct the MazeManager. */
  @Inject
  public MazeManagerImpl(Maze maze, boolean difficulty) {

    this.mazeObject = maze;
    this.difficulty = difficulty;
    // Randomly delete maze walls so that there is a path from the starting block to the exit.
    mazeObject.createRandomMaze();
    mazeObject.createPlayer();
  }

  /** @return the character instance from this classes maze instance. */
  public Character getMazePlayer() {
    return mazeObject.player;
  }
  /** @return the coin instance from this classes maze instance. */
  public Coin getCoin() {
    return mazeObject.getCoin();
  }

  /** @return the first teleport maze block instance from this classes maze instance. */
  public MazeBlock getTeleportBlock1() {
    return mazeObject.getTeleportBlock1();
  }

  /** @return the second teleport maze block instance from this classes maze instance. */
  public MazeBlock getTeleportBlock2() {
    return mazeObject.getTeleportBlock2();
  }

  /** @return the list of maze walls from this classes maze instance. */
  public List<Wall> getMazeWalls() {
    return mazeObject.getMazeWalls();
  }
  /** @return the list of outer maze walls from this classes maze instance. */
  public List<Wall> getOuterWalls() {
    return mazeObject.getOuterWalls();
  }

  /** @return the mazeObject for this maze manager. */
  public Maze getMazeObject() {
    return mazeObject;
  }

  /**
   * Return true if and only if the character is on the winning block and the user has one the game.
   *
   * @return Whether the player is currently standing on the winning block or not.
   */
  public boolean checkWin() {
    return mazeObject.player.getCurrentBlock() == mazeObject.getWinningBlock();
  }

  /**
   * If the player obtains the coin, increase the points of the user and remove the coin from the
   * maze.
   */
  public void gotCoin() {
    if (mazeObject.player.getCurrentBlock() == mazeObject.getCoin().getMazeBlock()
        && !(mazeObject.getCoin().isVisited())) {
      mazeObject.removeCoin();
    }
  }

  /**
   * If the player steps onto a teleporting maze block, move the user to the other teleporting maze
   * block and remove the two teleporting maze blocks from the maze.
   */
  public void teleport() {

    if (mazeObject.player.getCurrentBlock() == mazeObject.getTeleportBlock1()) {
      mazeObject.player.setCurrentBlock(mazeObject.getTeleportBlock2());
      mazeObject.removeTeleportBlocks();
    } else if (mazeObject.player.getCurrentBlock() == mazeObject.getTeleportBlock2()) {
      mazeObject.player.setCurrentBlock(mazeObject.getTeleportBlock1());
      mazeObject.removeTeleportBlocks();
    }
  }

  /**
   * Calculates the score of the player and returns it
   *
   * @return The updated score of the player after they move
   */
  public int calculateScore() {
    if (difficulty) { // Easy game produces less points than the extreme version of the game.
      if (mazeObject.getCoin().isVisited()) {
        return (int) (15000 / Math.pow((1.1), mazeObject.player.getMoves()) + 5000);
      }
      return (int) (15000 / Math.pow((1.1), mazeObject.player.getMoves()));
    } else {
      if (mazeObject.getCoin().isVisited()) {
        return (int) (20000 / Math.pow((1.1), mazeObject.player.getMoves()) + 5000);
      }
      return (int) (20000 / Math.pow((1.1), mazeObject.player.getMoves()));
    }
  }

  /**
   * Get the x coordinate of the player
   *
   * @return the x coordinate of the player
   */
  public int getPlayerX() {
    return mazeObject.player.coordinateX();
  }

  /**
   * Get the y coordinate of the player
   *
   * @return the y coordinate of the player
   */
  public int getPlayerY() {
    return mazeObject.player.coordinateY();
  }

  /**
   * Move the player according to the direction d given.
   *
   * @param d the direction that the user wants to move the maze character.
   */
  public void movePlayer(Character.Direction d) {
    if (d == Character.Direction.DOWN) {
      mazeObject.player.move(Character.Direction.DOWN);
    } else if (d == Character.Direction.UP) {
      mazeObject.player.move(Character.Direction.UP);
    } else if (d == Character.Direction.LEFT) {
      mazeObject.player.move(Character.Direction.LEFT);
    } else if (d == Character.Direction.RIGHT) {
      mazeObject.player.move(Character.Direction.RIGHT);
    }
  }
}
