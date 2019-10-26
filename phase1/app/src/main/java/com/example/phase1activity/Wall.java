package com.example.phase1activity;

public class Wall extends MazeItem {

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
    }

    /**
     * Checks whether or not this wall goes from left to right (true) or from up to down (false)
     *
     * @return true iff the wall goes from left to right
     */
    public boolean isHorz() {
        return horizontal;
    }


}
