package com.example.phase1activity.ui.leaderboard;

import com.example.phase1activity.domain.leaderboard.GlobalStats;

/** A leaderboard presenter. */
public class LeaderboardPresenterImpl implements LeaderboardPresenter {
  /** An object that reports user high scores, in order, sorted by various types of scores. */
  private GlobalStats globalStats;

  /** This presenter's view. */
  private LeaderboardView view;

  /** Initialize this presenter, set its view to leaderBoardView, and set its globalStats object. */
  public LeaderboardPresenterImpl(LeaderboardView leaderBoardView, GlobalStats globalStats) {
    this.view = leaderBoardView;
    this.globalStats = globalStats;
  }

  /** Sort the inputs to the text fields of this presenter's view by best scores. */
  @Override
  public void presentBestScores() {
    view.setTextFields(globalStats.getUsersToBestScores());
  }

  /** Sort the inputs to the text fields of this presenter's view by best scores. */
  @Override
  public void presentMostMoves() {
    view.setTextFields(globalStats.getUsersToMostMoves());
  }

  /** Sort the inputs to the text fields of this presenter's view by best scores. */
  @Override
  public void presentFastestReactions() {
    view.setTextFields(globalStats.getUsersToFastestReactions());
  }
}
