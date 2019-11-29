package com.example.phase1activity.Core.Transmission.Overseers;

import android.content.Context;

import com.example.phase1activity.Core.Transmission.Saving.AndroidSaver;
import com.example.phase1activity.Core.Transmission.Saving.ISaver;
import com.example.phase1activity.UI.MenuScreens.DaggerLeaderboardComponent;
import com.example.phase1activity.UI.MenuScreens.LeaderBoardByMoves;
import com.example.phase1activity.UI.MenuScreens.LeaderBoardByReactionTime;
import com.example.phase1activity.UI.MenuScreens.LeaderBoardByScore;
import com.example.phase1activity.UI.MenuScreens.LeaderBoardSorting;
import com.example.phase1activity.UI.MenuScreens.LeaderboardModule;

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

  /** Sorting strategy for displaying the best users on the leaderboard. */
  private LeaderBoardSorting sorter;

  /**
   * Tell Dagger how to create this object.
   *
   * @param context the Context creating this class.
   */
  @Inject
  public GlobalStats(Context context) {
    iSaver = new AndroidSaver(context);
  }

  public void updateGlobalStats() {
    this.usersWithBestScores = getSortedPlayers(SortType.BY_BEST_SCORE);
    this.usersWithMostMoves = getSortedPlayers(SortType.BY_MOST_MOVES);
    this.usersWithFastestReactions = getSortedPlayers(SortType.BY_FASTEST_REACTION);
  }

  /** Enum for choosing what sort of sorting strategy to use. */
  public enum SortType {
    BY_BEST_SCORE,
    BY_MOST_MOVES,
    BY_FASTEST_REACTION
  }

  /**
   * Sort the players based on the desired statistic, and return the sorted list.
   *
   * @param type enum describing by which statistic to sort by.
   * @return a sorted list of players, along with the statistic.
   */
  private List<List<Object>> getSortedPlayers(SortType type) {
    // Inject sorter using dagger.
    sorter =
        DaggerLeaderboardComponent.builder()
            .leaderboardModule(new LeaderboardModule(type))
            .build()
            .injectLeaderBoardSorting();

    return sorter.sortPlayers(iSaver);
  }

  /** @return a list of players sorted by total score, along with the statistic. */
  public List<List<Object>> getUsersToBestScores() {
    return getSortedPlayers(SortType.BY_BEST_SCORE);
  }

  /** @return a list of players sorted by most moves, along with the statistic. */
  public List<List<Object>> getUsersToMostMoves() {
    return getSortedPlayers(SortType.BY_MOST_MOVES);
  }

  /** @return a list of players sorted by fastest reaction time, along with the statistic. */
  public List<List<Object>> getUsersToFastestReactions() {
    return getSortedPlayers(SortType.BY_FASTEST_REACTION);
  }

  Map<String, Double> getUserBestScore() {
    Double total = 0.0;
    String name = "";
    Map<String, Double> temp = new HashMap<>();
    for (String username : iSaver.getHighScores().keySet()) {
      double score =
          iSaver.getHighScores().get(username).get(AndroidSaver.AttributeType.TOTAL_SCORE);
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
