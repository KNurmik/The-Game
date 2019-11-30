package com.example.phase1activity.Core.Logic.MazeGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/** A character class. */
public class Character {

  /** The maze block that the character is in. */
  private MazeBlock currentBlock;
  /** The number of moves this character has taken in the maze. */
  private int moves;
  /** Paint attribute for the character */
  private Paint paintText = new Paint();

  /**
   * Initialize a MazeBlock, and set its initial appearance.
   *
   * @param block the MazeBlock instance that the character is on.
   */
  Character(MazeBlock block) {
    currentBlock = block;
    moves = 0;
    paintText.setTextSize(80);
    paintText.setColor(Color.BLUE);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
  }

  /**
   * Change the block that the character is located in.
   *
   * @param currentBlock the MazeBlock that the character will go to.
   */
  void setCurrentBlock(MazeBlock currentBlock) {
    this.currentBlock = currentBlock;
  }

  /**
   * Getter for currentBlock attribut.
   *
   * @return The current maze block that the character is on.
   */
  MazeBlock getCurrentBlock() {
    return currentBlock;
  }

  /**
   * Set the color of the character object in the maze.
   *
   * @param color the color of the character.
   */
  public void setPaintText(int color) {
    paintText.setColor(color);
  }

  /** @return the number of moves that the character has made each game */
  public int getMoves() {
    return moves;
  }

  /** @param moves Increments the player's moves by moves */
  public void setMoves(int moves) {
    this.moves += moves;
  }

  /**
   * Move the character in <direction> if and only if there is no wall blocking it.
   *
   * @param direction the direction that the user wishes the character to move in.
   */
  void move(Direction direction) {
    switch (direction) {
      case UP: // moves up if the MazeItem above is a MazeBlock
        if (currentBlock.getUp() instanceof MazeBlock) {
          setCurrentBlock((MazeBlock) currentBlock.getUp());
          moves++;
        }
        break;
      case DOWN: // moves down if the MazeItem above is a MazeBlock
        if (currentBlock.getDown() instanceof MazeBlock) {
          setCurrentBlock((MazeBlock) currentBlock.getDown());
          moves++;
        }
        break;
      case LEFT: // moves left if the MazeItem above is a MazeBlock
        if (currentBlock.getLeft() instanceof MazeBlock) {
          setCurrentBlock((MazeBlock) currentBlock.getLeft());
          moves++;
        }

        break;
      case RIGHT: // moves right if the MazeItem above is a MazeBlock
        if (currentBlock.getRight() instanceof MazeBlock) {
          setCurrentBlock((MazeBlock) currentBlock.getRight());
          moves++;
        }
        break;
    }
  }

  /**
   * Return the x coordinate of the character in Android coordinates.
   *
   * @return the x coordinate of the character in Android coordinates.
   */
  int coordinateX() {
    return currentBlock.getX() * 100 + 173;
  }

  /**
   * Return the y coordinate of the character in Android coordinates.
   *
   * @return the y coordinate of the character in Android coordinates.
   */
  int coordinateY() {
    return currentBlock.getY() * 100 + 210;
  }

  /**
   * Draw the character.
   *
   * @param canvas the canvas that the device is using.
   */
  void draw(Canvas canvas) {
    canvas.drawCircle(coordinateX(), coordinateY(), 40, paintText);
  }

  /** Possible directions that the character could move. */
  public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }
}
