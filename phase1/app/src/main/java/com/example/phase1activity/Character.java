package com.example.phase1activity;

public class Character extends MazeItem{

    public MazeBlock currentBlock;

    public Character(double a, double b) {
        super(a, b);
    }

    enum Direction{
        UP, DOWN, LEFT, RIGHT
    }

    public void move(Direction direction){
        switch(direction){
            case UP:

        }
    }
}
