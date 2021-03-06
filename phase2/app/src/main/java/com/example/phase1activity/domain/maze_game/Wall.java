package com.example.phase1activity.domain.maze_game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/** A maze wall. */
public class Wall extends MazeItem {

  /** Return true iff this wall is horizontal. */
  private final boolean horizontal;

  private Paint paintText = new Paint();

  /**
   * Construct a Wall object at (a, b).
   *
   * @param a The initial x value of the MazeItem
   * @param b The initial y value of the MazeItem
   * @param horz Whether this wall is horizontal or vertical
   */
  Wall(int a, int b, boolean horz) {
    super(a, b);
    horizontal = horz;

    paintText.setStrokeWidth(10);
    paintText.setColor(Color.BLACK);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
  }

  /**
   * Return true if this wall is horizontal, and false otherwise.
   *
   * @return true iff the wall is horizontal.
   */
  boolean isHorz() {
    return horizontal;
  }

  /**
   * Draw this Wall.
   *
   * @param canvas the canvas to draw the Wall onto.
   */
  public void draw(Canvas canvas) {
    if (isHorz()) {
      canvas.drawLine(
          (float) (getX() * 100 + 125),
          (float) (getY() * 100 + 160),
          (float) (getX() * 100 + 225),
          (float) (getY() * 100 + 160),
          paintText);
    } else {
      canvas.drawLine(
          (float) (getX() * 100 + 125),
          (float) (getY() * 100 + 160),
          (float) (getX() * 100 + 125),
          (float) (getY() * 100 + 260),
          paintText);
    }
  }
}
