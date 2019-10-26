package com.example.phase1activity;

public class Character {

    public MazeBlock currentBlock;

    public Character(MazeBlock block) {
        currentBlock = block;
    }

    enum Direction{
        UP, DOWN, LEFT, RIGHT
    }

    public void move(Direction direction){
        switch(direction){
            case UP:
                if (currentBlock.getUp() instanceof MazeBlock){
                    currentBlock = (MazeBlock) currentBlock.getUp();
                }
                break;
            case DOWN:
                if (currentBlock.getDown() instanceof MazeBlock){
                    currentBlock = (MazeBlock) currentBlock.getDown();
                }
                break;
            case LEFT:
                if (currentBlock.getLeft() instanceof MazeBlock){
                    currentBlock = (MazeBlock) currentBlock.getLeft();
                }
                break;
            case RIGHT:
                if (currentBlock.getRight() instanceof MazeBlock){
                    currentBlock = (MazeBlock) currentBlock.getRight();
                }
                break;
        }
    }
}
