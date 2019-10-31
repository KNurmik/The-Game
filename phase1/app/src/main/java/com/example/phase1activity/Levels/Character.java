package com.example.phase1activity.Levels;

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
    MazeBlock currentBlock;

    /**
     * The number of moves this character has taken in the maze.
     */
    public int moves;

    /**
     * Pain attribute for the character
     */
    private Paint paintText = new Paint();

    /**
     * @param block the current MazeBlock instance that the character is on
     */
    public Character(MazeBlock block) {
        currentBlock = block;
        moves = 0;

        paintText.setTextSize(80);
        paintText.setColor(Color.BLUE);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
    }

    /**
     * Possible directions that the character could move
     */
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Method that moves the character in <direction> if and only if there is no wall blocking it
     *
     * @param direction the direction that the user wishes the character to move in
     */
    public void move(Direction direction) {
        switch (direction) {
            case UP: // moves up if the MazeItem above is a MazeBlock
                if (currentBlock.getUp() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getUp();
                    moves++;
                }
                break;
            case DOWN: // moves down if the MazeItem above is a MazeBlock
                if (currentBlock.getDown() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getDown();
                    moves++;
                }
                break;
            case LEFT: // moves left if the MazeItem above is a MazeBlock
                if (currentBlock.getLeft() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getLeft();
                    moves++;
                }

                break;
            case RIGHT: // moves right if the MazeItem above is a MazeBlock
                if (currentBlock.getRight() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getRight();
                    moves++;
                }
                break;

        }
    }

    /**
     * Draws the character
     *
     * @param canvas the canvas that the phone is using
     */
    public void draw(Canvas canvas) {
        canvas.drawCircle(currentBlock.getX() * 100 + 150, currentBlock.getY() * 100 + 160, 35, paintText);
    }
}
