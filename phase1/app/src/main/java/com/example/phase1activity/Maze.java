package com.example.phase1activity;

public class Maze {

    public ArrayList<Wall> walls;
    public ArrayList<MazeBlock> blocks;

    public MazeBlock winBlock;

    public Maze(){
        walls = new ArrayList<>();
        blocks = new ArrayList<>();
    }
}
