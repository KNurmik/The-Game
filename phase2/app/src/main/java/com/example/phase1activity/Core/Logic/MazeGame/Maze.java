package com.example.phase1activity.Core.Logic.MazeGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Maze {

  /** An instance of the character that this maze is using. */
  public Character player;
  /** The block that the player needs to get to in order to win. */
  public MazeBlock winningBlock;
  /** A list containing all of the walls in the maze */
  List<Wall> mazeWalls;
  /** The height of the maze */
  private int mazeHeight;
  /** The Width of the maze */
  private int mazeWidth;
  /** A 2D array storing all the MazeBlocks in the maze */
  private MazeBlock[][] mazeBlocks;

  /**
   * A constructor for the Maze. Creates the whole maze with all the Walls and MazeBlocks
   *
   * @param width The width of the maze
   * @param height The height of the maze
   */
  Maze(int width, int height) {
    mazeHeight = height;
    mazeWidth = width;

    mazeWalls = new ArrayList<>();
    mazeBlocks = new MazeBlock[mazeWidth][mazeHeight];

    createMaze();
  }

  void createPlayer() {
    this.player = new Character(mazeBlocks[0][mazeHeight - 1]);
  }

  /**
   * Creates the Maze itself, adds all the Walls and MazeBlocks to the lists and arrays they belong
   * in.
   */
  private void createMaze() {
    Stack<MazeBlock> stack = new Stack<>();
    MazeBlock currentBlock;

    for (int i = 0; i < mazeWidth; i++) {
      for (int j = 0; j < mazeHeight; j++) {
        mazeBlocks[i][j] = new MazeBlock(i, j);
        // The leftmost walls are added
        if (i == 0) {
          Wall newWall = new Wall(i, j, false);
          mazeBlocks[i][j].setLeft(newWall);
          mazeWalls.add(newWall);
        } else { // Creates the left wall for the mazeBlock
          Wall newWall = new Wall(i, j, false);
          mazeBlocks[i][j].setLeft(newWall);
          mazeBlocks[i - 1][j].setRight(newWall);
          mazeBlocks[i][j].addNeighbour(mazeBlocks[i - 1][j]);
          mazeBlocks[i - 1][j].addNeighbour(mazeBlocks[i][j]);
          mazeWalls.add(newWall);
        }
        // The rightmost walls are added
        if (i == mazeWidth - 1) {
          Wall newWall = new Wall(i + 1, j, false);
          mazeBlocks[i][j].setRight(newWall);
          mazeWalls.add(newWall);
        }

        // The topmost walls are added
        if (j == 0) {
          if (i != mazeWidth - 1) {
            Wall newWall = new Wall(i, j, true);
            mazeBlocks[i][j].setUp(newWall);
            mazeWalls.add(newWall);
          }
        } else { // Creates a vertical link between the MazeBlocks at (i, j) and (i, j-1)
          Wall newWall = new Wall(i, j, true);
          mazeBlocks[i][j].setUp(newWall);
          mazeBlocks[i][j - 1].setDown(newWall);
          mazeBlocks[i][j].addNeighbour(mazeBlocks[i][j - 1]);
          mazeBlocks[i][j - 1].addNeighbour(mazeBlocks[i][j]);
          mazeWalls.add(newWall);
        }
        // The bottommost walls are added
        if (j == mazeHeight - 1) {
          Wall newWall = new Wall(i, j + 1, true);
          mazeBlocks[i][j].setDown(newWall);
          mazeWalls.add(newWall);
        }
      }
    }

    currentBlock = mazeBlocks[0][0];
    currentBlock.visited = true;
    do {
      MazeBlock nextBlock = currentBlock.randomNeighbour();
      if (nextBlock != null) {
        removeWall(currentBlock, nextBlock);
        stack.push(currentBlock);
        currentBlock = nextBlock;
        currentBlock.visited = true;

      } else {
        currentBlock = stack.pop();
      }
    } while (!stack.empty());

    // The winning block is always in the Top right corner of the maze
    winningBlock = mazeBlocks[mazeWidth - 1][0];
  }

  public void removeWall(MazeBlock current, MazeBlock next) {
    if (current.getRight() == next.getLeft()) {
      deleteWall(current.getRight());
      next.createHorzLink(current);
    }
    if (current.getLeft() == next.getRight()) {
      deleteWall(current.getLeft());
      current.createHorzLink(next);
    }
    if (current.getDown() == next.getUp()) {
      deleteWall(current.getDown());
      next.createVertLink(current);
    }
    if (current.getUp() == next.getDown()) {
      deleteWall(current.getUp());
      current.createVertLink(next);
    }
  }

  private void deleteWall(MazeItem wall) {

    for (int i = 0; i < mazeWalls.size(); i++) {
      if (mazeWalls.get(i) == wall) {
        mazeWalls.remove(i);
        break;
      }
    }
  }

}
