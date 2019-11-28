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
    // Randomly deletes maze walls such that there is a path from the starting block to the exit.
    mazeObject.createRandomMaze();
    mazeObject.createPlayer();
  }

  /**
   * Draws every outer wall of the maze as well as any walls that are neighbours of neighbours of
   * the currentBlock that the player is on , as well as the player
   *
   * @param canvas The canvas that the phone will use
   */
  public void draw(Canvas canvas) {
    mazeObject.player.draw(canvas);
    for (Wall wall : mazeObject.mazeWalls) {
      for (MazeBlock currentNeighbour : mazeObject.player.currentBlock.getNeighbours()) {
        for (MazeBlock currentNeighbour2 : currentNeighbour.getNeighbours()) {
          for (MazeItem neighbourWall : currentNeighbour2.getNeighbourWalls()) {
            if (wall
                == neighbourWall) { // If the wall is a wall of the currentNeighbour or
                                    // currentNeighbour2 (which is a neighbour of currentNeighbour)
              wall.draw(canvas);
            }
          }
        }
      }
    }
    for (Wall wall : mazeObject.outerWalls) {
      wall.draw(canvas);
    }
  }
}
