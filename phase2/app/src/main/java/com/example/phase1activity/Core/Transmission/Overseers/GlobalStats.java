package com.example.phase1activity.Core.Transmission.Overseers;

import android.content.Context;

import com.example.phase1activity.Core.Transmission.Saving.AndroidSaver;
import com.example.phase1activity.Core.Transmission.Saving.ISaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class GlobalStats {

  private ISaver iSaver;
  public List<List<Object>> usersWithBestScores;
  public List<List<Object>> usersWithFastestReactions;
  public List<List<Object>> usersWithMostMoves;

  @Inject
  public GlobalStats(Context context) {
    iSaver = new AndroidSaver(context);
  }

  public void updateGlobalStats() {
    this.usersWithBestScores = getUsersToBestScores();
    this.usersWithMostMoves = getUsersToMostMoves();
    this.usersWithFastestReactions = getUsersToFastestReactions();
  }

  private List<List<Object>> getUsersToBestScores() {
    usersWithBestScores = new ArrayList<>();
    for (String username : iSaver.getHighScores().keySet()) {
      int userBestScore =
              iSaver.getHighScores().get(username).get(AndroidSaver.AttributeType.TOTAL_SCORE).intValue();
      List<Object> listEntry = new ArrayList<>();
      listEntry.add(0, username);
      listEntry.add(1, userBestScore);

      boolean userAddedToList = false;

      for (int i = 0; i < usersWithBestScores.size(); i++) {
        if (userBestScore > (int) usersWithBestScores.get(i).get(1)) {
          usersWithBestScores.add(i, listEntry);
          userAddedToList = true;
          break;
        }
      }
      if (!userAddedToList) {
        usersWithBestScores.add(listEntry);
      }
    }
    return usersWithBestScores;
  }

  private List<List<Object>> getUsersToMostMoves() {
    usersWithMostMoves = new ArrayList<>();
    for (String username : iSaver.getHighScores().keySet()) {
      int userBestMoves =
              iSaver.getHighScores().get(username).get(AndroidSaver.AttributeType.TOTAL_MOVES).intValue();
      List<Object> listEntry = new ArrayList<>();
      listEntry.add(0, username);
      listEntry.add(1, (int) userBestMoves);

      boolean userAddedToList = false;

      for (int i = 0; i < usersWithMostMoves.size(); i++) {
        if (userBestMoves > (int) usersWithMostMoves.get(i).get(1)) {
          usersWithMostMoves.add(i, listEntry);
          userAddedToList = true;
          break;
        }
      }
      if (!userAddedToList) {
        usersWithMostMoves.add(listEntry);
      }
    }
    return usersWithMostMoves;
  }

  private List<List<Object>> getUsersToFastestReactions() {
    usersWithFastestReactions = new ArrayList<>();
    for (String username : iSaver.getHighScores().keySet()) {
      double userFastestReaction =
              iSaver.getHighScores().get(username).get(AndroidSaver.AttributeType.FASTEST_RXN_TIME);
      List<Object> listEntry = new ArrayList<>();
      listEntry.add(0, username);
      listEntry.add(1, userFastestReaction);

      boolean userAddedToList = false;

      for (int i = 0; i < usersWithFastestReactions.size(); i++) {
        if (userFastestReaction < (double) usersWithFastestReactions.get(i).get(1)) {
          usersWithFastestReactions.add(i, listEntry);
          userAddedToList = true;
          break;
        }
      }
      if (!userAddedToList) {
        usersWithFastestReactions.add(listEntry);
      }
    }
    return usersWithFastestReactions;
  }

  Map<String, Double> getUserBestScore() {
    Double total = 0.0;
    String name = "";
    Map<String, Double> temp = new HashMap<>();
    for (String username : iSaver.getHighScores().keySet()) {
      double score = iSaver.getHighScores().get(username).get(AndroidSaver.AttributeType.TOTAL_SCORE);
      if (score > total) {
        total = score;
        name = username;
      }
    }
    temp.put(name, total);
    return temp;
  }

  Map<String, Double> getUserMostMoves() {
    double total = 0;
    String name = "None";
    Map<String, Double> temp = new HashMap<>();
    Map<String, Map<AndroidSaver.AttributeType, String>> test1 = iSaver.getExistingUserData();
    for (String username : iSaver.getHighScores().keySet()) {
      double score =
              iSaver.getHighScores().get(username).get(AndroidSaver.AttributeType.TOTAL_MOVES);
      if (score > total) {
        total = score;
        name = username;
      }
    }
    temp.put(name, total);
    return temp;
  }

  Map<String, Double> getUserFastestReaction() {
    Double total = 1.0;
    String name = "Admin";
    Map<String, Double> temp = new HashMap<>();

    for (String username : iSaver.getHighScores().keySet()) {
      Double score =
              iSaver.getHighScores().get(username).get(AndroidSaver.AttributeType.FASTEST_RXN_TIME);
      if (score < total) {
        total = score;
        name = username;
      }
    }
    temp.put(name, total);
    return temp;
  }
}
