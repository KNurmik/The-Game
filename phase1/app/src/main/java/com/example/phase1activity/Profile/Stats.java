package com.example.phase1activity.Profile;
import java.util.ArrayList;
import java.util.Collections;

public class Stats {
    private int mazeScore;
    private int cardScore;
    private int reactionScore;

    void setMazeScore(int n){
        this.mazeScore = n;
    }

    void setCardScore(int n){
        this.cardScore = n;
    }

    void setReactionScore(int n){
        this.reactionScore = n;
    }

    int getMazeScore(){
        return this.mazeScore;
    }

    int getCardScore(){
        return this.cardScore;
    }

    int getReactionScore(){
        return this.reactionScore;
    }
}
