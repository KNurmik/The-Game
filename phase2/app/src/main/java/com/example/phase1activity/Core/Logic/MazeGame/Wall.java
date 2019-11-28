package com.example.phase1activity.Core.Logic.MazeGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/** A maze wall. */
class Wall extends MazeItem {

  /** Whether this wall is horizontal or vertical. */
  private final boolean horizontal;
  /** A string representing this wall's appearance. */
  private String appearance;

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

    if (horz) {
      appearance = "_";
    } else {
      appearance = "|";
    }

    paintText.setStrokeWidth(10);
    paintText.setColor(Color.BLACK);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
  }

  /**
   * Return true if this wall is horizontal, and false otherwise.
   *
   * @return true iff the wall goes from left to right.
   */
  boolean isHorz() {
    return horizontal;
  }

  /**
   * Draw the Wall on the phone screen.
   *
   * @param canvas the canvas to draw the Wall onto.
   */
  void draw(Canvas canvas) {
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
