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
                    mazeBlocks[i][j].setLeft(new Wall(i, j, false));
                }
                else {
                    mazeBlocks[i][j].createHorzLink(mazeBlocks[i-1][j]);
                }
                if (i == mazeWidth - 1){
                    mazeBlocks[i][j].setRight(new Wall(i, j, false));
                }

                if (j == 0) {
                    mazeBlocks[i][j].setLeft(new Wall(i, j, true));
                }
                else {
                    mazeBlocks[i][j].createVertLink(mazeBlocks[i][j-1]);
                }
                if (j == mazeHeight - 1){
                    mazeBlocks[i][j].setRight(new Wall(i, j, true));
                }
            }
        }

        winningBlock = mazeBlocks[mazeWidth - 1][0];
    }

    public void addWall(double x, double y, boolean horz){
        mazeWalls.add(new Wall(x, y, horz));
    }

}
