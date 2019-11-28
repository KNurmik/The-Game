package com.example.phase1activity.Core.Transmission.Overseers;

import android.content.Context;

import com.example.phase1activity.Core.Transmission.Saving.AndroidSaver;
import com.example.phase1activity.Core.Transmission.Saving.ISaver;

import java.util.HashMap;
import java.util.Map;

public class GlobalStats {

    ISaver iSaver;

    public String totalName;
    String reactionName;
    String movesName;
    Double highestScore;
    Double fastestReaction;
    Double leastMoves;
    Map<AndroidSaver.AttributeType, Double> test;


    public GlobalStats(Context context, String one, String two, String three, Double score, Double reaction, Double moves){
        iSaver = new AndroidSaver(context);
        totalName = one;
        reactionName = two;
        movesName = three;
        highestScore = score;
        fastestReaction = reaction;
        leastMoves = moves;
    }
    public void updateGlobalStats(){
        this.totalName = String.join("", getBestTotal().keySet());
        this.reactionName = String.join("", getBestReaction().keySet());
        this.movesName = String.join("", getBestReaction().keySet());

        this.highestScore = getBestTotal().get(totalName);
        this.fastestReaction = getBestReaction().get(reactionName);
        this.leastMoves = getBestMoves().get(movesName);
    }


    public Map<String, Double> getBestTotal(){
        Double total = 0.0;
        String name = "None";
        Map<String, Double> temp = new HashMap<>();
        for(String username : iSaver.getHighScores().keySet()){
            test = iSaver.getHighScores().get(username);
            Double score = test.get(AndroidSaver.AttributeType.TOTAL_SCORE);
            if(score > total){
                total = score;
                name = username;
            }
        }
        temp.put(name, total);
        return temp;

    }

    Map<String, Double> getBestMoves(){
        Double total = 0.0;
        String name = "None";
        Map<String, Double> temp = new HashMap<>();

        for(String username : iSaver.getHighScores().keySet()){
            Double score = iSaver.getHighScores().get(username).get(AndroidSaver.AttributeType.TOTAL_MOVES);
            if(score < total){
                total = score;
                name = username;
            }
        }
        temp.put(name, total);
        return temp;

    }

    Map<String, Double> getBestReaction(){
        Double total = 0.0;
        String name = "None";
        Map<String, Double> temp = new HashMap<>();

        for(String username : iSaver.getHighScores().keySet()){
            Double score = iSaver.getHighScores().get(username).get(AndroidSaver.AttributeType.FASTEST_RXN_TIME);
            if(score < total){
                total = score;
                name = username;
            }
        }
        temp.put(name, total);
        return temp;

    }

}
