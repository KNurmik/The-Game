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
  public Maze(int width, int height) {
    mazeHeight = height;
    mazeWidth = width;

    mazeWalls = new ArrayList<>();
    mazeBlocks = new MazeBlock[mazeWidth][mazeHeight];

    createMaze();
  }

  /** Creates a player at the bottom left block */
  void createPlayer() {
    this.player = new Character(mazeBlocks[0][mazeHeight - 1]);
  }

  /**
   * Creates the Maze itself, adds all the Walls and MazeBlocks it's respective arrays and lists.
   */
  private void createMaze() {

    // Draws all the possible walls first and make the walls the left, right, up, down of the
    // surrounding maze blocks.
    for (int i = 0; i < mazeWidth; i++) {
      for (int j = 0; j < mazeHeight; j++) {
        mazeBlocks[i][j] = new MazeBlock(i, j);
        // The leftmost walls are added
        if (i == 0) {
          Wall newWall = new Wall(i, j, false);
          mazeBlocks[i][j].setLeft(newWall);
          mazeWalls.add(newWall);
        } else { // The left walls for the mazeBlock
          Wall newWall = new Wall(i, j, false);
          mazeBlocks[i][j].setLeft(newWall);
          mazeBlocks[i - 1][j].setRight(newWall);
          mazeBlocks[i][j].addNeighbour(mazeBlocks[i - 1][j]);
          mazeBlocks[i - 1][j].addNeighbour(mazeBlocks[i][j]);
          mazeWalls.add(newWall);
        }
        // The right walls for the  mazeBlock
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
        } else { // The top walls for the mazeBlock
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

    createRandomMaze(); // Randomly generate a maze
    // The winning block is always in the top right corner of the maze.
    winningBlock = mazeBlocks[mazeWidth - 1][0];
  }

  /**
   * Randomly remove walls between maze blocks until there is at least one path connecting evey maze
   * block
   */
  private void createRandomMaze() {
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
            stack
                .pop(); // If the currentBlock has neighbours that have all been visited, remove it
                        // from the stack
      }
    } while (!stack
        .empty()); // Repeat this process until all maze blocks have at least one path to one
                   // another
  }
  /**
   * Removes the wall between the current and next maze block
   *
   * @param currentBlock one of the maze blocks
   * @param nextBlock the other maze block
   */
  public void removeWall(MazeBlock currentBlock, MazeBlock nextBlock) {
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

  /** @param wall that needs to be deleted from the mazeWalls list. */
  private void deleteWall(MazeItem wall) {

    for (int i = 0; i < mazeWalls.size(); i++) {
      if (mazeWalls.get(i) == wall) {
        mazeWalls.remove(i);
        break;
      }
    }
  }
}
