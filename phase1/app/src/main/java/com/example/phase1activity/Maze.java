package com.example.phase1activity;
import java.util.ArrayList;

public class Maze {

    public Character player;

    public int mazeHeight;
    public int mazeWidth;

    public ArrayList<Wall> mazeWalls;
    public ArrayList<MazeBlock> mazeBlocks;
    public MazeBlock winningBlock;

    public MazeBlock[][] blocks;

    public Maze(int width, int height){
        mazeHeight = height;
        mazeWidth = width;

        mazeWalls = new ArrayList<>();
        mazeBlocks = new ArrayList<>();
        blocks = new MazeBlock[mazeWidth][mazeHeight];

        player = new Character(0, mazeHeight - 1);
        createMaze();
    }

    public void createMaze(){
        for (int i = 0; i < mazeWidth; i++){
            for (int j = 0; j < mazeHeight; j++){
                if (i == j){
                    addWall(i, 0, true);
                    addWall(i, mazeHeight, true);
                    addWall(0, j, false);
                    addWall(mazeWidth, j, false);
                }
                mazeBlocks.add(new MazeBlock(i, j));
                blocks[i][j] = new MazeBlock(i, j);
            }
        }

        winningBlock = blocks[mazeWidth - 1][0];
    }

    public void addWall(double x, double y, boolean horz){
        mazeWalls.add(new Wall(x, y, horz));
    }

}
