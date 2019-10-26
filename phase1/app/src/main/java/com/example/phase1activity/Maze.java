package com.example.phase1activity;
import java.util.ArrayList;

public class Maze {

    public Character player;

    public int mazeHeight;
    public int mazeWidth;

    public ArrayList<Wall> mazeWalls;
    public MazeBlock winningBlock;

    public MazeBlock[][] mazeBlocks;

    public Maze(int width, int height){
        mazeHeight = height;
        mazeWidth = width;

        mazeWalls = new ArrayList<>();
        mazeBlocks = new MazeBlock[mazeWidth][mazeHeight];

        player = new Character(0, mazeHeight - 1);
        createMaze();
    }

    public void createMaze(){
        for (int i = 0; i < mazeWidth; i++){
            for (int j = 0; j < mazeHeight; j++){
                mazeBlocks[i][j] = new MazeBlock(i, j);

                if (i == 0) {
                    mazeBlocks[0][j].setLeft(new Wall(0, j, false));
                }
            }
        }

        winningBlock = mazeBlocks[mazeWidth - 1][0];
    }

    public void addWall(double x, double y, boolean horz){
        mazeWalls.add(new Wall(x, y, horz));
    }

}
