package com.example.phase1activity.Core.Logic.MazeGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import javax.inject.Inject;

public class MazeManagerImpl implements MazeManager {

  /** The maze that will be displayed on the phone. */
  private Maze mazeObject;

  private boolean difficulty;
  /** Constructs the MazeManager. */
  @Inject
  public MazeManagerImpl(Maze maze, boolean difficulty) {

    this.mazeObject = maze;
    this.difficulty = difficulty;
    // Randomly deletes maze walls such that there is a path from the starting block to the exit.
    mazeObject.createRandomMaze();
    mazeObject.createPlayer();
  }

  /** @return the mazeObject for this maze manager. */
  public Maze getMazeObject() {
    return mazeObject;
  }

  // TODO: drawing should be done in the view, commanded by the presenter.
  // TODO: create helper methods, this method is too long.
  /**
   * Draw every outer wall of the maze as well as any walls that are neighbours of neighbours of the
   * currentBlock that the player is on, as well as the player.
   *
   * @param canvas the canvas.
   */
  public void draw(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(Color.BLACK);
    paint.setTextSize(75);
    mazeObject.player.draw(canvas);
    mazeObject.getCoin().draw(canvas);
    // Draw the teleporting blocks if they have not been accessed yet.
    if (mazeObject.getTeleportBlock1() != null) {
      canvas.drawCircle(
          mazeObject.getTeleportBlock1().getX() * 100 + 173,
          mazeObject.getTeleportBlock1().getY() * 100 + 210,
          40,
          paint);
      canvas.drawCircle(
          mazeObject.getTeleportBlock2().getX() * 100 + 173,
          mazeObject.getTeleportBlock2().getY() * 100 + 210,
          40,
          paint);
    }

    for (Wall wall : mazeObject.getOuterWalls()) {
      wall.draw(canvas);
    }
    for (Wall wall : mazeObject.getMazeWalls()) {
      if (!difficulty) { // If the user chooses the extreme setting, only draw walls that are near
        // the character in the maze/
        for (MazeBlock currentNeighbour :
            mazeObject.player.getCurrentBlock().getNeighboursNeighbour()) {
          for (MazeItem neighbourWall : currentNeighbour.getNeighbourWalls()) {
            if (wall == neighbourWall) { // If the wall is a wall of the currentNeighbour or
              // currentNeighbour2 (which is a neighbour of currentNeighbour)
              wall.draw(canvas);
            }
          }
        }

      } else { // Draw all walls in the maze if the user chose the easy setting.
        for (Wall wall2 : mazeObject.getMazeWalls()) {
          wall2.draw(canvas);
        }
      }
    }
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
