package com.example.phase1activity.UI.MenuScreens;

import com.example.phase1activity.Core.Transmission.Saving.AndroidSaver;
import com.example.phase1activity.Core.Transmission.Saving.ISaver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/** Strategy for sorting players by most moves. */
public class LeaderBoardByMoves implements LeaderBoardSorting {

  /** Tell Dagger how to create this object. */
  @Inject
  public LeaderBoardByMoves() {}

  /**
   * Sort the players based on total moves made, return a sorted list of players along with their
   * statistic.
   *
   * @param iSaver the saver to read data from.
   * @return a list of players and their statistics.
   */
  public List<List<Object>> sortPlayers(ISaver iSaver) {
    List<List<Object>> usersWithMostMoves = new ArrayList<>();
    for (String username : iSaver.getHighScores().keySet()) {
      double userBestMoves =
          iSaver.getHighScores().get(username).get(AndroidSaver.AttributeType.TOTAL_MOVES);
      List<Object> listEntry = new ArrayList<>();
      listEntry.add(0, username);
      listEntry.add(1, userBestMoves);

      boolean userAddedToList = false;

      for (int i = 0; i < usersWithMostMoves.size(); i++) {
        if (userBestMoves > (double) usersWithMostMoves.get(i).get(1)) {
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
}
