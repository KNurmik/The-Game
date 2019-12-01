package com.example.phase1activity.ui.leaderboard;

import java.util.List;

/** A leaderboard view. */
public interface LeaderboardView {
  /** Set the text of all TextView objects to the stats of their respective users in sortedUsers. */
  void setTextFields(List<List<Object>> sortedUsers);
}
