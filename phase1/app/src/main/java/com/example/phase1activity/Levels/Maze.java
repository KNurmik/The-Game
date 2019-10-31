package com.example.phase1activity.Levels;

import java.util.ArrayList;

import android.graphics.Canvas;

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
     * A list containing all of the walls in the maze
     */
    public ArrayList<Wall> mazeWalls;

    /**
     * A 2D array storing all the MazeBlocks in the maze
     */
    public MazeBlock[][] mazeBlocks;

    /**
     * The block that the player needs to get to in order to win.
     */
    public MazeBlock winningBlock;


    /**
     * A constructor for the Maze. Creates the whole maze with all the Walls and MazeBlocks
     *
     * @param width  The width of the maze
     * @param height The height of the maze
     */
    public Maze(int width, int height, ArrayList<Wall> innerWalls) {
        mazeHeight = height;
        mazeWidth = width;

        mazeWalls = new ArrayList<>();
        mazeBlocks = new MazeBlock[mazeWidth][mazeHeight];

        createMaze();
        makeInnerWalls(innerWalls);

    }

    public void createPlayer() {
        player = new Character(mazeBlocks[0][mazeHeight - 1]);
    }

    /**
     * Creates the Maze itself, adds all the Walls and MazeBlocks to the lists and arrays they
     * belong in.
     */
    public void createMaze() {
        for (int i = 0; i < mazeWidth; i++) {
            for (int j = 0; j < mazeHeight; j++) {
                mazeBlocks[i][j] = new MazeBlock(i, j);

                //The leftmost walls are added
                if (i == 0) {
                    Wall newWall = new Wall(i, j, false);
                    mazeBlocks[i][j].setLeft(newWall);
                    mazeWalls.add(newWall);
                } else { //Creates a horizontal link between the MazeBlocks at (i, j) and (i-1, j)
                    mazeBlocks[i][j].createHorzLink(mazeBlocks[i - 1][j]);
                }
                //The rightmost walls are added
                if (i == mazeWidth - 1) {
                    Wall newWall = new Wall(i + 1, j, false);
                    mazeBlocks[i][j].setRight(newWall);
                    mazeWalls.add(newWall);
                }

                //The topmost walls are added
                if (j == 0) {
                    if (i != mazeWidth - 1) {
                        Wall newWall = new Wall(i, j, true);
                        mazeBlocks[i][j].setUp(newWall);
                        mazeWalls.add(newWall);
                    }
                } else { //Creates a vertical link between the MazeBlocks at (i, j) and (i, j-1)
                    mazeBlocks[i][j].createVertLink(mazeBlocks[i][j - 1]);
                }
                //The bottommost walls are added
                if (j == mazeHeight - 1) {
                    Wall newWall = new Wall(i, j + 1, true);
                    mazeBlocks[i][j].setDown(newWall);
                    mazeWalls.add(newWall);
                }
            }
        }

        //The winning block is always in the Top right corner of the maze
        winningBlock = mazeBlocks[mazeWidth - 1][0];
    }

    /**
     * Adds a new wall to the maze
     *
     * @param newWall the wall instances that will be added to the maze
     */
    public void addWall(Wall newWall) {
        int x = newWall.getX();
        int y = newWall.getY();
        boolean horz = newWall.isHorz();

        mazeWalls.add(newWall);

        //Breaks a link between two MazeBlocks and puts a wall between them.
        if (newWall.isHorz()) {
            mazeBlocks[x][y].setUp(newWall);
            mazeBlocks[x][y - 1].setDown(newWall);
        } else {
            mazeBlocks[x][y].setLeft(newWall);
            mazeBlocks[x - 1][y].setRight(newWall);
        }
    }

    /**
     * Adds all the walls in innerWalls array list to the mazeWalls array list
     *
     * @param innerWalls list of inner walls that will added ot the mazeWalls array list
     */
    public void makeInnerWalls(ArrayList<Wall> innerWalls) {
        for (int i = 0; i < innerWalls.size(); i++) {
            addWall(innerWalls.get(i));
        }
    }

}
