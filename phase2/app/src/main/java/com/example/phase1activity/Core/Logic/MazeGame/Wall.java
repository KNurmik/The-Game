package com.example.phase1activity.Core.Logic.MazeGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

class Wall extends MazeItem {

  /** Whether this wall is horizontal or vertical */
  private final boolean horizontal;
  /** Represents what the Wall looks like on the screen */
  private String appearance;

  private Paint paintText = new Paint();

  /**
   * The constructor for a wall
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
   * Checks whether or not this wall goes from left to right (true) or from up to down (false)
   *
   * @return true iff the wall goes from left to right
   */
  boolean isHorz() {
    return horizontal;
  }

  /**
   * Draws the Wall on the phone screen
   *
   * @param canvas The canvas that the Wall instance will be drawn on
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
