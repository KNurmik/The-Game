package com.example.phase1activity.domain.maze_game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/** A coin class for the maze game. */
public class Coin {

  /** The maze block that the character is in. */
  private MazeBlock mazeBlock;
  /** Paint attribute for the character */
  private Paint paintText = new Paint();
  /** Boolean variable representing if the character has reached the coin */
  private boolean visited;

  Coin(MazeBlock block) {
    mazeBlock = block;
    paintText.setColor(Color.YELLOW);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    visited = false;
  }

  MazeBlock getMazeBlock() {
    return mazeBlock;
  }

  boolean isVisited() {
    return visited;
  }

  void setVisited(boolean visited) {
    this.visited = visited;
  }

  /**
   * Return the x coordinate of the character in Android coordinates.
   *
   * @return the x coordinate of the character in Android coordinates.
   */
  private int coordinateX() {
    return mazeBlock.getX() * 100 + 173;
  }

  /**
   * Return the y coordinate of the character in Android coordinates.
   *
   * @return the y coordinate of the character in Android coordinates.
   */
  private int coordinateY() {
    return mazeBlock.getY() * 100 + 210;
  }

  /**
   * Draw the coin.
   *
   * @param canvas the canvas that the device is using.
   */
  public void draw(Canvas canvas) {
    if (!visited) {
      canvas.drawCircle(coordinateX(), coordinateY(), 40, paintText);
    }
  }
}
