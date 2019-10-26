package com.example.phase1activity;

public class MazeBlock extends MazeItem {

    private MazeBlock up;
    private MazeBlock down;
    private MazeBlock right;
    private MazeBlock left;

    public MazeBlock(double a, double b){
        super(a, b);
        up = null;
        down = null;
        right = null;
        left = null;
    }

    public MazeBlock getUp() {
        return up;
    }

    public void setUp(MazeBlock up) {
        this.up = up;
    }

    public MazeBlock getDown() {
        return down;
    }

    public void setDown(MazeBlock down) {
        this.down = down;
    }

    public MazeBlock getRight() {
        return right;
    }

    public void setRight(MazeBlock right) {
        this.right = right;
    }

    public MazeBlock getLeft() {
        return left;
    }

    public void setLeft(MazeBlock left) {
        this.left = left;
    }
}
