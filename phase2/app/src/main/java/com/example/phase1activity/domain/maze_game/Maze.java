package com.example.phase1activity.domain.maze_game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/** A maze for the maze game */
public class Maze {

  /** The character that this maze is using. */
  public Character player;
  /** The coin in the maze. */
  private Coin coin;
  /** The block that the player needs to get to in order to win. */
  private MazeBlock winningBlock;
  /** The first teleporting block. */
  private MazeBlock teleportBlock1;
  /** The second teleporting block. */
  private MazeBlock teleportBlock2;
  /** A list containing all of the walls in the maze. */
  private List<Wall> mazeWalls;
  /** A list containing all the outer walls of the maze */
  private List<Wall> outerWalls;
  /** The height of the maze. */
  private int mazeHeight;
  /** The Width of the maze. */
  private int mazeWidth;
  /** A 2D array storing all the MazeBlocks in the maze. */
  private MazeBlock[][] mazeBlocks;

  /**
   * A constructor for the Maze. Create the entire maze with all Walls and MazeBlocks.
   *
   * @param width The width of the maze
   * @param height The height of the maze
   */
  public Maze(int width, int height) {
    mazeHeight = height;
    mazeWidth = width;
    teleportBlock1 = null;
    teleportBlock2 = null;
    mazeWalls = new ArrayList<>();
    outerWalls = new ArrayList<>();
    mazeBlocks = new MazeBlock[mazeWidth][mazeHeight];
    createMaze();
  }

  /** @return list of all the maze walls. */
  List<Wall> getMazeWalls() {
    return mazeWalls;
  }
  /** @return list of all the outer walls of the maze. */
  List<Wall> getOuterWalls() {
    return outerWalls;
  }
  /** @return the first teleport maze block. */
  MazeBlock getTeleportBlock1() {
    return teleportBlock1;
  }
  /** @return the second teleport maze block. */
  MazeBlock getTeleportBlock2() {
    return teleportBlock2;
  }
  /** @return the exiting maze block. */
  MazeBlock getWinningBlock() {
    return winningBlock;
  }
  /** @return the coin in the maze. */
  Coin getCoin() {
    return coin;
  }
  /** Create a player at the bottom left block of the maze. */
  void createPlayer() {
    this.player = new Character(mazeBlocks[0][mazeHeight - 1]);
  }

  /** Create the coin and teleporting blocks by randomly assigning a unique Maze Block to each. */
  private void createFeatures() {
    ArrayList<Integer> col = new ArrayList<>();
    for (int i = 1; i < 8; i++) {
      col.add(i);
    }
    Collections.shuffle(col); // Randomize a collection of possible columns for the features
    ArrayList<Integer> row = new ArrayList<>();
    for (int i = 1; i < 8; i++) {
      row.add(i);
    }
    Collections.shuffle(row); // Randomize a collection of possible rows for the features

    this.coin =
        new Coin(
            mazeBlocks[col.get(0)][
                row.get(0)]); // Assign the first int in the col and row collection to the coin

    mazeBlocks[col.get(1)][row.get(1)].setTeleportBlock(
        true); // Set this maze block to a teleport block
    teleportBlock1 =
        mazeBlocks[col.get(1)][
            row.get(1)]; // Assign the second int in the col and row collection to the coin
    mazeBlocks[col.get(2)][row.get(2)].setTeleportBlock(
        true); // Set this maze block to a teleport block
    teleportBlock2 =
        mazeBlocks[col.get(2)][
            row.get(2)]; // Assign the third int in the col and row collection to the coin
  }

  /** Set the visited coin attribute to true so that the coin no longer exists in the maze. */
  void removeCoin() {
    this.coin.setVisited(true);
  }

  /** Set the teleport maze blocks to null so that they no longer exist in the maze. */
  void removeTeleportBlocks() {
    this.teleportBlock1 = null;
    this.teleportBlock2 = null;
  }
  /** Create the maze, and add all the Walls and MazeBlocks in it's respective collections. */
  private void createMaze() {

    // Draw all the possible walls first and make the walls the left, right, up, down of the
    // surrounding maze blocks.
    for (int i = 0; i < mazeWidth; i++) {
      for (int j = 0; j < mazeHeight; j++) {
        mazeBlocks[i][j] = new MazeBlock(i, j);

        // The leftmost walls are added.
        if (i == 0) {
          Wall newWall = new Wall(i, j, false);
          mazeBlocks[i][j].setLeft(newWall);
          mazeWalls.add(newWall);
          outerWalls.add(newWall);
        } else {
          Wall newWall = new Wall(i, j, false);
          mazeBlocks[i][j].setLeft(newWall);
          mazeBlocks[i - 1][j].setRight(newWall);
          mazeBlocks[i][j].addNeighbour(mazeBlocks[i - 1][j]);
          mazeBlocks[i - 1][j].addNeighbour(mazeBlocks[i][j]);
          mazeWalls.add(newWall);
        }
        // The right walls for the mazeBlock.
        if (i == mazeWidth - 1) {
          Wall newWall = new Wall(i + 1, j, false);
          mazeBlocks[i][j].setRight(newWall);
          mazeWalls.add(newWall);
          outerWalls.add(newWall);
        }

        // Add the top-most walls.
        if (j == 0) {
          if (i != mazeWidth - 1) {
            Wall newWall = new Wall(i, j, true);
            mazeBlocks[i][j].setUp(newWall);
            mazeWalls.add(newWall);
            outerWalls.add(newWall);
          }
        } else {
          Wall newWall = new Wall(i, j, true);
          mazeBlocks[i][j].setUp(newWall);
          mazeBlocks[i][j - 1].setDown(newWall);
          mazeBlocks[i][j].addNeighbour(mazeBlocks[i][j - 1]);
          mazeBlocks[i][j - 1].addNeighbour(mazeBlocks[i][j]);
          mazeWalls.add(newWall);
        }
        // Add the bottom-most walls.
        if (j == mazeHeight - 1) {
          Wall newWall = new Wall(i, j + 1, true);
          mazeBlocks[i][j].setDown(newWall);
          mazeWalls.add(newWall);
          outerWalls.add(newWall);
        }
      }
    }
    // Add the coin and teleporting blocks to the maze.
    createFeatures();
    // The winning block is always in the top right corner of the maze.
    winningBlock = mazeBlocks[mazeWidth - 1][0];
  }

  /**
   * Randomly remove walls between maze blocks until there is at least one path connecting evey maze
   * block
   */
  void createRandomMaze() {
    Stack<MazeBlock> stack = new Stack<>();
    MazeBlock currentBlock;
    currentBlock = mazeBlocks[0][0]; // Set the currentBlock to the bottom left block
    currentBlock.visited = true;
    do {
      MazeBlock nextBlock =
          currentBlock.randomNeighbour(); // Pick a random neighbour of currentBlock
      if (nextBlock != null) {
        removeWall(currentBlock, nextBlock);
        stack.push(currentBlock); // Push the currentBlock onto the stack
        currentBlock = nextBlock; // Do the same to nextBlock
        currentBlock.visited = true;

      } else {
        currentBlock =
            stack.pop(); // If the currentBlock has neighbours that have all been visited, remove it
        // from the stack.
      }
    } while (!stack
        .empty()); // Repeat this process until all maze blocks have at least one path to one
    // another.
  }
  /**
   * Remove the wall between the current and next maze block.
   *
   * @param currentBlock one of the maze blocks
   * @param nextBlock the other maze block
   */
  private void removeWall(MazeBlock currentBlock, MazeBlock nextBlock) {
    if (currentBlock.getRight() == nextBlock.getLeft()) {
      deleteWall(currentBlock.getRight()); // Delete the wall between the maze blocks
      nextBlock.createHorzLink(
          currentBlock); // Creates a 'link' between two MazeBlocks that are to the left and right
      // of each other
    }
    if (currentBlock.getLeft() == nextBlock.getRight()) {
      deleteWall(currentBlock.getLeft());
      currentBlock.createHorzLink(nextBlock);
    }
    if (currentBlock.getDown() == nextBlock.getUp()) {
      deleteWall(currentBlock.getDown());
      nextBlock.createVertLink(
          currentBlock); // Creates a 'link' between two MazeBlocks that are to the above and below
      // of each other
    }
    if (currentBlock.getUp() == nextBlock.getDown()) {
      deleteWall(currentBlock.getUp());
      currentBlock.createVertLink(nextBlock);
    }
  }

  /**
   * Delete wall from mazeWalls.
   *
   * @param wall that needs to be deleted from the mazeWalls list.
   */
  private void deleteWall(MazeItem wall) {

    for (int i = 0; i < mazeWalls.size(); i++) {
      if (mazeWalls.get(i) == wall) {
        mazeWalls.remove(i);
        break;
      }
    }
  }
}
