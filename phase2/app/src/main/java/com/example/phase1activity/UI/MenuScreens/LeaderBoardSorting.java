package com.example.phase1activity.UI.MenuScreens;

import com.example.phase1activity.Core.Transmission.Saving.ISaver;

import java.util.List;

/** Interface for sorting players for a global leaderboard. */
public interface LeaderBoardSorting {

  /**
   * Sort the list of players and return a list of players and their statistics.
   *
   * @param saver the ISaver object to be used for
   * @return a list of players along with their statistic.
   */
  List<List<Object>> sortPlayers(ISaver saver);
}
