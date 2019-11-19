package com.example.phase1activity.Domain.MazeGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * A character class for the user's character.
 */

public class Character {

    /**
     * Changes the block that the character is located in.
     *
     * @param currentBlock the MazeBlock that the character will go to.
     */
    private void setCurrentBlock(MazeBlock currentBlock) {
        this.currentBlock = currentBlock;
    }

    /**
     * The current maze block that the character is in.
     */
    public MazeBlock currentBlock;

    /**
     * The number of moves this character has taken in the maze.
     */
    public int moves;

    /**
     * Pain attribute for the character
     */
    private Paint paintText = new Paint();

    /**
     * Initialize a MazeBlock, and set its initial appearance.
     *
     * @param block the current MazeBlock instance that the character is on.
     */
    Character(MazeBlock block) {
        currentBlock = block;
        moves = 0;
        paintText.setTextSize(80);
        paintText.setColor(Color.BLUE);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public void setPaintText(int color) {
        paintText.setColor(color);
    }

    /**
     * Possible directions that the character could move.
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Method that moves the character in <direction> if and only if there is no wall blocking it.
     *
     * @param direction the direction that the user wishes the character to move in
     */
    public void move(Direction direction) {
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
    public int coordinateX(){
        return currentBlock.getX() * 100 + 300;
    }

    /**
     * Return the y coordinate of the character in Android coordinates.
     *
     * @return the y coordinate of the character in Android coordinates.
     */
    public int coordinateY(){
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
}
