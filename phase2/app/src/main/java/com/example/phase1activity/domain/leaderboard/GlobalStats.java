package com.example.phase1activity.domain.leaderboard;

import android.content.Context;

import com.example.phase1activity.service.AndroidSaver;
import com.example.phase1activity.service.ISaver;
import com.example.phase1activity.ui.leaderboard.DaggerLeaderboardComponent;
import com.example.phase1activity.ui.leaderboard.LeaderboardModule;

import java.util.List;

import javax.inject.Inject;

/**
 * Responsible for obtaining sorted lists of users and their greatest statistics.
 */
public class GlobalStats {

  /** The android saver. */
  private ISaver iSaver;

  /**
   * A list of users and their respective score sorted by highest score.
   */
  private List<List<Object>> usersWithBestScores;
  /**
   * A list of users and their respective reaction time sorted by lowest reaction time.
   */
  private List<List<Object>> usersWithFastestReactions;
  /**
   * A list of users and their respective moves sorted by most moves.
   */
  private List<List<Object>> usersWithMostMoves;

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

  /** Updates the three sorted lists of users and their best statistics */
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
}
