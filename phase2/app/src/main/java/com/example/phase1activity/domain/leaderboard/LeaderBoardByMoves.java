package com.example.phase1activity.domain.leaderboard;

import com.example.phase1activity.service.AndroidSaver;
import com.example.phase1activity.service.Saver;

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
   * @param saver the saver to read data from.
   * @return a list of players and their statistics.
   */
  public List<List<Object>> sortPlayers(Saver saver) {
    List<List<Object>> usersWithMostMoves = new ArrayList<>();
    for (String username : saver.getHighScores().keySet()) {
      int userBestMoves =
          saver
              .getHighScores()
              .get(username)
              .get(AndroidSaver.AttributeType.TOTAL_MOVES)
              .intValue();
      String nickname =
          saver.getExistingUserData().get(username).get(Saver.AttributeType.NICKNAME);

      // A smaller list storing the user's nickname and their number of moves.
      List<Object> listEntry = new ArrayList<>();
      listEntry.add(0, nickname);
      listEntry.add(1, userBestMoves);

      boolean userAddedToList = false;

      // Inserts the list entry into the sorted list
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
}
