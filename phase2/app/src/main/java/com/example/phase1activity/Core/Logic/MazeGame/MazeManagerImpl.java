package com.example.phase1activity.Core.Logic.MazeGame;

import java.util.List;

import javax.inject.Inject;

public class MazeManagerImpl implements MazeManager {

  /** The maze that will be displayed on the phone. */
  private Maze mazeObject;

  /** Constructs the MazeManager. */
  @Inject
  public MazeManagerImpl(Maze maze) {

    this.mazeObject = maze;
    // Randomly deletes maze walls such that there is a path from the starting block to the exit.
    mazeObject.createRandomMaze();
    mazeObject.createPlayer();
  }

  public Character getMazePlayer() {
    return mazeObject.player;
  }

  public Coin getCoin() {
    return mazeObject.getCoin();
  }

  public MazeBlock getTeleportBlock1() {
    return mazeObject.getTeleportBlock1();
  }

  public MazeBlock getTeleportBlock2() {
    return mazeObject.getTeleportBlock2();
  }

  public List<Wall> getMazeWalls() {
    return mazeObject.getMazeWalls();
  }

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
    if (mazeObject.getCoin().isVisited()) {
      return (int) (20000 / Math.pow((1.1), mazeObject.player.getMoves()) + 6000);
    }
    return (int) (20000 / Math.pow((1.1), mazeObject.player.getMoves()) + 1000);
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
