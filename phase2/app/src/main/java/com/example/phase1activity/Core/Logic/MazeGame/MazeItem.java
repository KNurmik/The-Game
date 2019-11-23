package com.example.phase1activity.Core.Logic.MazeGame;


public class MazeItem {

    /**
     * The MazeItem's x coordinate
     */
    private int x;

    /**
     * The MazeItem's y coordinate
     */
    private int y;

    /**
     * The MazeItem constructor
     * @param a The initial x value of the MazeItem
     * @param b The initial y value of the MazeItem
     */
    MazeItem(int a, int b) {
        x = a;
        y = b;
    }

    /**
     * Getter for MazeItem.x
     * @return x
     */
    int getX() {
        return x;
    }

    /**
     * Setter for MazeItem.x
     * @param x The new x value of this MazeItem
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter for MazeItem.y
     * @return y
     */
    int getY() {
        return y;
    }

    /**
     * Setter for MazeItem.y
     * @param y The new y value of this MazeItem
     */
    public void setY(int y) {
        this.y = y;
    }

}
