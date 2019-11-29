package com.example.phase1activity.Core.Logic.MazeGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import javax.inject.Inject;

public class MazeManager {

  /** The maze that will be displayed on the phone */
  public Maze mazeObject;

  /** Constructs the MazeManager */
  @Inject
  public MazeManager(Maze maze) {

    this.mazeObject = maze;

    // Randomly deletes maze walls such that there is a path from the starting block to the exit.
    mazeObject.createRandomMaze();
    mazeObject.createPlayer();
    mazeObject.createCoin();
  }

  /**
   * Draws every outer wall of the maze as well as any walls that are neighbours of neighbours of
   * the currentBlock that the player is on , as well as the player
   *
   * @param canvas The canvas that the phone will use
   */
  public void draw(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(Color.BLACK);
    paint.setTextSize(75);
    mazeObject.player.draw(canvas);
    mazeObject.coin.draw(canvas);
    if (mazeObject.teleportBlock1 != null) {
      canvas.drawCircle(
          mazeObject.teleportBlock1.getX() * 100 + 173,
          mazeObject.teleportBlock1.getY() * 100 + 210,
          40,
          paint);
      canvas.drawCircle(
          mazeObject.teleportBlock2.getX() * 100 + 173,
          mazeObject.teleportBlock2.getY() * 100 + 210,
          40,
          paint);
    }

    for (Wall wall : mazeObject.outerWalls) {
      wall.draw(canvas);
    }
    for (Wall wall : mazeObject.mazeWalls) {
      for (MazeBlock currentNeighbour : mazeObject.player.currentBlock.getNeighboursNeighbour()) {
        for (MazeItem neighbourWall : currentNeighbour.getNeighbourWalls()) {
          if (wall == neighbourWall) { // If the wall is a wall of the currentNeighbour or
            // currentNeighbour2 (which is a neighbour of currentNeighbour)
            wall.draw(canvas);
          }
        }
      }
    }
  }

  /**
   * Returns true if and only if the character is on the winning block and the user has one the game
   *
   * @return Whether the player is currently standing on the winning block or not.
   */
  public boolean checkWin() {
    return mazeObject.player.currentBlock == mazeObject.winningBlock;
  }

  public void gotCoin() {
    if (mazeObject.player.currentBlock == mazeObject.coin.mazeBlock
        && !(mazeObject.coin.isVisited())) {
      mazeObject.removeCoin();
    }
  }

  public void teleport() {

    if (mazeObject.player.currentBlock == mazeObject.teleportBlock1) {
      mazeObject.player.currentBlock = mazeObject.teleportBlock2;
      mazeObject.removeTeleportBlocks();
    } else if (mazeObject.player.currentBlock == mazeObject.teleportBlock2) {
      mazeObject.player.currentBlock = mazeObject.teleportBlock1;
      mazeObject.removeTeleportBlocks();
    }
  }

  /**
   * Calculates the score of the player and returns it
   *
   * @return The updated score of the player after they move
   */
  public int calculateScore() {
    if (mazeObject.coin.isVisited()) {
      return (int) (20000 / Math.pow((1.1), mazeObject.player.moves) + 6000);
    }
    return (int) (20000 / Math.pow((1.1), mazeObject.player.moves) + 1000);
  }

  public int getPlayerX() {
    return mazeObject.player.coordinateX();
  }

  public int getPlayerY() {
    return mazeObject.player.coordinateY();
  }

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
