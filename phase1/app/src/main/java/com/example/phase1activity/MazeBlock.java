package com.example.phase1activity;

public class MazeBlock extends MazeItem {

    private MazeItem up;
    private MazeItem down;
    private MazeItem right;
    private MazeItem left;

    public MazeBlock(int a, int b) {
        super(a, b);
        up = null;
        down = null;
        right = null;
        left = null;
    }

    public MazeItem getUp() {
        return up;
    }

    public void setUp(MazeItem up) {
        this.up = up;
    }

    public MazeItem getDown() {
        return down;
    }

    public void setDown(MazeItem down) {
        this.down = down;
    }

    public MazeItem getRight() {
        return right;
    }

    public void setRight(MazeItem right) {
        this.right = right;
    }

    public MazeItem getLeft() {
        return left;
    }

    public void setLeft(MazeItem left) {
        this.left = left;
    }

    public void createVertLink(MazeBlock other) {
        setUp(other);
        other.setDown(this);
    }

    public void createHorzLink(MazeBlock other) {
        setLeft(other);
        other.setRight(this);
    }
}
