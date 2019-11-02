package com.example.phase1activity.Profile;

import android.graphics.Color;

public class Profile {
    String username;
    String password;
    int gameLevel;
    String colour;
    int song;
    String nickname;
    double fastestRxnStat;
    int totalScoreStat;
    int totalMovesStat;


    public Profile(String username, String password, String nickname, String colour, int gameLevel, int song, double fastestReactionTime, int totalScore, int totalMoves) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.colour = colour;
        this.gameLevel = gameLevel;
        this.song = song;
        this.fastestRxnStat = fastestReactionTime;
        this.totalScoreStat = totalScore;
        this.totalMovesStat = totalMoves;
    }

    public String getUsername() {
        return this.username;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getColour() {
        if(colour.equals("red")){return Color.RED;}
        else if(colour.equals("blue")){return Color.BLUE;}
        else{return Color.GREEN;}
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getGameLevel(){
        return this.gameLevel;
    }
    public void setGameLevel(int n){
        this.gameLevel = n;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setSong(int n) {
        this.song = n;
    }

    public int getSong(){
        return this.song;
}

    public void setFastestRxnStat(double time){this.fastestRxnStat = time;}





}
