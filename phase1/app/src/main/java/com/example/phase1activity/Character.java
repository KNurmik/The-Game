package com.example.phase1activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * A character class for the user's character
 */

public class Character {

    /**
     * The current maze block that the character is in
     */
    public MazeBlock currentBlock;

    /**
     * The symbol that resembles the character in the maze
     */
    public String appearance;

    /**
     * Pain attribute for the character
     */
    private Paint paintText = new Paint();

    /**
     *
     * @param block the current MazeBlock instance that the character is on
     */
    public Character(MazeBlock block) {
        currentBlock = block;

        paintText.setTextSize(36);
        paintText.setColor(Color.BLUE);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);

        appearance = "o";
    }

    /**
     * Possible directions that the character could move
     */
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Method that moves the character in <direction> if and only if there is no wall blocking it
     * @param direction the direction that the user wishes the character to move in
     */
    public void move(Direction direction) {
        switch (direction) {
            case UP: // moves up if the MazeItem above is a MazeBlock
                if (currentBlock.getUp() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getUp();
                }
                break;
            case DOWN: // moves down if the MazeItem above is a MazeBlock
                if (currentBlock.getDown() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getDown();
                }
                break;
            case LEFT: // moves left if the MazeItem above is a MazeBlock
                if (currentBlock.getLeft() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getLeft();
                }
                break;
            case RIGHT: // moves right if the MazeItem above is a MazeBlock
                if (currentBlock.getRight() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getRight();
                }
                break;
        }
    }

    /**
     * Draws the character
     * @param canvas the canvas that the phone is using
     */
    public void draw(Canvas canvas) {
        canvas.drawText(appearance, currentBlock.getX(), currentBlock.getY(), paintText);
    }
}
