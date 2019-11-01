package com.example.phase1activity.Levels;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Wall extends MazeItem {

    /**
     * Represents what the Wall looks like on the screen
     */
    public String appearance;

    private Paint paintText = new Paint();

    /**
     * Whether this wall is horizontal or vertical
     */
    public final boolean horizontal;

    /**
     * The constructor for a wall
     *
     * @param a    The initial x value of the MAzeItem
     * @param b    The initial y value of the MazeItem
     * @param horz Whether this wall is horizontal or vertical
     */
    public Wall(int a, int b, boolean horz) {
        super(a, b);
        horizontal = horz;

        if (horz) {
            appearance = "_";
        } else {
            appearance = "|";
        }

        paintText.setTextSize(36);
        paintText.setColor(Color.BLACK);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
    }

    /**
     * Checks whether or not this wall goes from left to right (true) or from up to down (false)
     *
     * @return true iff the wall goes from left to right
     */
    public boolean isHorz() {
        return horizontal;
    }

    /**
     * Draws the Wall on the phone screen
     *
     * @param canvas The canvas that the Wall instance will be drawn on
     */
    public void draw(Canvas canvas) {
        if (isHorz()) {
            canvas.drawLine((float) (getX() * 100 + 250), (float) (getY() * 100 + 160), (float) (getX() * 100 + 350), (float) (getY() * 100 + 160), paintText);
        } else {
            canvas.drawLine((float) (getX() * 100 + 250), (float) (getY() * 100 + 160), (float) (getX() * 100 + 250), (float) (getY() * 100 + 260), paintText);

        }

    }

}
