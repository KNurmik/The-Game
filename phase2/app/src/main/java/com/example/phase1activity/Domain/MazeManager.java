package com.example.phase1activity.Domain;

import java.util.ArrayList;
import android.graphics.Canvas;

public class MazeManager {

    /**
     * The maze that will be displayed on the phone
     */
    public Maze mazeObject;

    /**
     * Constructs the MazeManager
     */
    public MazeManager(){
        ArrayList<Wall> walls = makeWalls();
        mazeObject = new Maze(5, 5, walls);
        mazeObject.createPlayer();
    }

    /**
     * Manual wall creation
                * @return A list of all the walls that will be added to the maze skeleton.
                */
        private ArrayList<Wall> makeWalls(){
            ArrayList<Wall> walls = new ArrayList<>();

            walls.add(new Wall(0,1, true));
            walls.add(new Wall(0,4, true));
            walls.add(new Wall(1,1, false));
            walls.add(new Wall(1,1, true));
            walls.add(new Wall(1,3, false));
            walls.add(new Wall(2,2, false));
            walls.add(new Wall(2,4, true));
            walls.add(new Wall(2,3, true));
            walls.add(new Wall(3,0, false));
            walls.add(new Wall(3,1, false));
            walls.add(new Wall(3,2, true));
            walls.add(new Wall(3,4, true));
            walls.add(new Wall(4,1, true));
            walls.add(new Wall(4,3, true));
            walls.add(new Wall(4,3, false));

        return walls;
    }

    /**
     * Draws every Wall in the maze, as well as the player
     * @param canvas The canvas that the phone will use
     */
    public void draw(Canvas canvas){
        for (Wall wall : mazeObject.mazeWalls){
            wall.draw(canvas);
        }
        mazeObject.player.draw(canvas);
    }
}
