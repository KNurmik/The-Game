package com.example.phase1activity.Profile;
import java.util.ArrayList;
import java.util.Collections;

public class Stats {
    ArrayList<Integer> mazeScore;
    ArrayList<Integer> cardScore;
    ArrayList<Integer> reactionScore;

    void setMazeScore(int n){
        this.mazeScore.add(n);
    }

    void setCardScore(int n){
        this.cardScore.add(n);
    }

    void setReactionScore(int n){
        this.reactionScore.add(n);
    }

    int getMazeScore(){
        return this.mazeScore.get(this.mazeScore.size()-1);
    }

    int getCardScore(){
        return this.cardScore.get(this.cardScore.size()-1);
    }

    int getReactionScore(){
        return this.reactionScore.get(this.reactionScore.size()-1);
    }

    int bestCardScore(){
        return Collections.max(this.cardScore);
    }

    int bestReactionScore(){
        return Collections.max(this.reactionScore);
    }

    int bestMazeScore() {
        return Collections.max(this.mazeScore);
    }

}
