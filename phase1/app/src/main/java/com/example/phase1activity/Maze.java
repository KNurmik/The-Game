package com.example.phase1activity;

import java.util.ArrayList;

public class Maze {

    /**
     * An instance of the character that this maze is using.
     */
    public Character player;

    /**
     * The height of the maze
     */
    public int mazeHeight;
    /**
     * The Width of the maze
     */
    public int mazeWidth;


    /**
     *
     */
    public ArrayList<Wall> mazeWalls;

    public MazeBlock[][] mazeBlocks;
    public MazeBlock winningBlock;

    public Maze(int width, int height) {
        mazeHeight = height;
        mazeWidth = width;

        mazeWalls = new ArrayList<>();
        mazeBlocks = new MazeBlock[mazeWidth][mazeHeight];

        createMaze();
        player = new Character(mazeBlocks[0][mazeHeight - 1]);
    }

    public void createMaze() {
        for (int i = 0; i < mazeWidth; i++) {
            for (int j = 0; j < mazeHeight; j++) {
                mazeBlocks[i][j] = new MazeBlock(i, j);

                if (i == 0) {
                    Wall newWall = new Wall(i, j, false);
                    mazeBlocks[i][j].setLeft(newWall);
                    mazeWalls.add(newWall);
                } else {
                    mazeBlocks[i][j].createHorzLink(mazeBlocks[i - 1][j]);
                }
                if (i == mazeWidth - 1) {
                    Wall newWall = new Wall(i, j, false);
                    mazeBlocks[i][j].setRight(newWall);
                    mazeWalls.add(newWall);
                }
                if (j == 0) {
                    Wall newWall = new Wall(i, j, true);
                    mazeBlocks[i][j].setUp(newWall);
                    mazeWalls.add(newWall);
                } else {
                    mazeBlocks[i][j].createVertLink(mazeBlocks[i][j - 1]);
                }
                if (j == mazeHeight - 1) {
                    Wall newWall = new Wall(i, j, true);
                    mazeBlocks[i][j].setDown(newWall);
                    mazeWalls.add(newWall);
                }
            }
        }

        winningBlock = mazeBlocks[mazeWidth - 1][0];
    }

    public void addWall(int x, int y, boolean horz) {
        Wall newWall = new Wall(x, y, horz);
        mazeWalls.add(newWall);

        if (newWall.isHorz()) {
            mazeBlocks[x][y].setUp(newWall);
            mazeBlocks[x][y - 1].setDown(newWall);
        } else {
            mazeBlocks[x][y].setLeft(newWall);
            mazeBlocks[x - 1][y].setRight(newWall);
        }
    }

}
