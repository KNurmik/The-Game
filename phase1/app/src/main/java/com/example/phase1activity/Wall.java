package com.example.phase1activity;

public class Wall extends MazeItem {

    public boolean horizontal;

    public Wall(double a, double b, boolean horz){
        super(a, b);
        horizontal = horz;
    }

    public boolean isHorz(){
        return horizontal;
    }
}
