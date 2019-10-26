package com.example.phase1activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint;

public class Character {

    public MazeBlock currentBlock;
    public String appearance;

    private Paint paintText =new Paint();

    public Character(MazeBlock block) {
        currentBlock = block;

        paintText.setTextSize(36);
        paintText.setColor(Color.BLUE);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);

        appearance = "o";
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                if (currentBlock.getUp() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getUp();
                }
                break;
            case DOWN:
                if (currentBlock.getDown() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getDown();
                }
                break;
            case LEFT:
                if (currentBlock.getLeft() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getLeft();
                }
                break;
            case RIGHT:
                if (currentBlock.getRight() instanceof MazeBlock) {
                    currentBlock = (MazeBlock) currentBlock.getRight();
                }
                break;
        }
    }
    public void draw(Canvas canvas) {
        canvas.drawText(appearance, currentBlock.getX(), currentBlock.getY(), paintText);
    }
}
