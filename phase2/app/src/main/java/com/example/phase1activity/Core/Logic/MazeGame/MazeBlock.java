package com.example.phase1activity.Core.Logic.MazeGame;

import java.util.ArrayList;
import java.util.Random;

public class MazeBlock extends MazeItem {

  /** Boolean variable whether the mazeBlock as a path to another maze block. */
  boolean visited;
  /** The MazeBlock or Wall above this MazeBlock. */
  private MazeItem up;
  /** The MazeBlock or Wall below this MazeBlock. */
  private MazeItem down;
  /** The MazeBlock or Wall to the right of this MazeBlock. */
  private MazeItem right;
  /** The MazeBlock or Wall to the left of this MazeBlock. */
  private MazeItem left;
  /** An array list of surrounding maze blocks. */
  private ArrayList<MazeBlock> neighbours;

  /**
   * Initialize this MazeBlock at position (a, b).
   *
   * @param a x-coordinate.
   * @param b y-coordinate.
   */
  MazeBlock(int a, int b) {
    super(a, b);
    up = null;
    down = null;
    right = null;
    left = null;
    visited = false;
    neighbours = new ArrayList<>();
  }

  /**
   * Add mazeBlock to this block's neighbours.
   *
   * @param mazeBlock is a neighbour of this.
   */
  public void addNeighbour(MazeBlock mazeBlock) {
    neighbours.add(mazeBlock);
  }

  /** @return a random neighbour of this or nothing if all it's neighbours have been visited */
  MazeBlock randomNeighbour() {
    ArrayList<MazeBlock> visitedNeighbours = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < neighbours.size(); i++) {
      MazeBlock currentNeighbour = neighbours.get(i);
      if (!(currentNeighbour.visited)) {
        visitedNeighbours.add(currentNeighbour);
      }
    }
    if (visitedNeighbours.size() > 0) {
      int index = random.nextInt(visitedNeighbours.size());
      return visitedNeighbours.get(index);
    }
    return null;
  }

  /**
   * Getter for MazeBlock.up
   *
   * @return up
   */
  MazeItem getUp() {
    return up;
  }

  /**
   * Setter for MazeBlock.up
   *
   * @param up The MazeBlock or Wall that will be above this MazeBlock
   */
  public void setUp(MazeItem up) {
    this.up = up;
  }

  /**
   * Getter for MazeBlock.down
   *
   * @return down
   */
  MazeItem getDown() {
    return down;
  }

  /**
   * Setter for MazeBlock.down
   *
   * @param down The MazeBlock or Wall that will be below this MazeBlock
   */
  void setDown(MazeItem down) {
    this.down = down;
  }

  /**
   * Getter for MazeBlock.right
   *
   * @return right
   */
  MazeItem getRight() {
    return right;
  }

  /**
   * Setter for MazeBlock.right
   *
   * @param right The MazeBlock or Wall that will be to the right of this MazeBlock
   */
  void setRight(MazeItem right) {
    this.right = right;
  }

  /**
   * Getter for MazeBlock.left
   *
   * @return left
   */
  MazeItem getLeft() {
    return left;
  }

  /**
   * Setter for MazeBlock.left
   *
   * @param left The MazeBlock or Wall that will be to the left of this MazeBlock
   */
  void setLeft(MazeItem left) {
    this.left = left;
  }

  /**
   * Create a 'link' between two MazeBlocks that are above and below each other
   *
   * @param other The MazeBlock that will be linked to this MazeBlock
   */
  void createVertLink(MazeBlock other) {
    setUp(other);
    other.setDown(this);
  }

  /**
   * Create a 'link' between two MazeBlocks that are to the left and right of each other
   *
   * @param other The MazeBlock that will be linked to this MazeBlock
   */
  void createHorzLink(MazeBlock other) {
    setLeft(other);
    other.setRight(this);
  }
}
