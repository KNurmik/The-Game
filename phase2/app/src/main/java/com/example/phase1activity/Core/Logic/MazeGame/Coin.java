package com.example.phase1activity.Core.Logic.MazeGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Coin {

  /** The maze block that the character is in. */
  public MazeBlock mazeBlock;
  /** Paint attribute for the character */
  private Paint paintText = new Paint();

  private boolean visited;

  Coin(MazeBlock block) {
    mazeBlock = block;
    paintText.setColor(Color.YELLOW);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    visited = false;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  /**
   * Return the x coordinate of the character in Android coordinates.
   *
   * @return the x coordinate of the character in Android coordinates.
   */
  public int coordinateX() {
    return mazeBlock.getX() * 100 + 173;
  }

  /**
   * Return the y coordinate of the character in Android coordinates.
   *
   * @return the y coordinate of the character in Android coordinates.
   */
  public int coordinateY() {
    return mazeBlock.getY() * 100 + 210;
  }

  /**
   * Draw the coin.
   *
   * @param canvas the canvas that the device is using.
   */
  void draw(Canvas canvas) {
    if (!visited) {
      canvas.drawCircle(coordinateX(), coordinateY(), 40, paintText);
    }
  }
}
