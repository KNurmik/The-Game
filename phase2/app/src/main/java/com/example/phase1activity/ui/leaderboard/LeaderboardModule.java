package com.example.phase1activity.ui.leaderboard;

import com.example.phase1activity.domain.leaderboard.GlobalStats;
import com.example.phase1activity.domain.leaderboard.LeaderBoardByMoves;
import com.example.phase1activity.domain.leaderboard.LeaderBoardByReactionTime;
import com.example.phase1activity.domain.leaderboard.LeaderBoardByScore;
import com.example.phase1activity.domain.leaderboard.LeaderBoardSorting;

import dagger.Module;
import dagger.Provides;

/** Dagger module for constructing objects of type LeaderBoardSorting. */
@Module
public class LeaderboardModule {

  /** Enum corresponding to the type of sorting to be used. */
  private GlobalStats.SortType sortType;

  public LeaderboardModule(GlobalStats.SortType type) {
    sortType = type;
  }

  /**
   * Provide object of type interface based on the type of sorting desired.
   *
   * @return one of the sorting strategies as LeaderBoardSorting.
   */
  @Provides
  LeaderBoardSorting provideLeaderBoardSorting() {
    // Sort users by total score.
    if (sortType.equals(GlobalStats.SortType.BY_BEST_SCORE)) {
      return new LeaderBoardByScore();
      // Sort users by most moves.
    } else if (sortType.equals(GlobalStats.SortType.BY_MOST_MOVES)) {
      return new LeaderBoardByMoves();
      // Sort users by fastest reaction time.
    } else {
      return new LeaderBoardByReactionTime();
    }
  }
}
