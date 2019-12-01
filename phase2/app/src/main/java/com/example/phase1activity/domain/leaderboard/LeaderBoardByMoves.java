package com.example.phase1activity.domain.leaderboard;

import com.example.phase1activity.service.AndroidSaver;
import com.example.phase1activity.service.ISaver;

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
   * @return a sorted list of players and their statistics.
   */
  public List<List<Object>> sortPlayers(ISaver iSaver) {
    List<List<Object>> usersWithMostMoves = new ArrayList<>();
    for (String username : iSaver.getHighScores().keySet()) {
      // Obtains the total number of moves the user has taken
      int userBestMoves =
              iSaver
                      .getHighScores()
                      .get(username)
                      .get(AndroidSaver.AttributeType.TOTAL_MOVES)
                      .intValue();
      String nickname =
              iSaver.getExistingUserData().get(username).get(ISaver.AttributeType.NICKNAME);

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
      // If the number of moves is not greater than any other entries in the list so far.
      if (!userAddedToList) {
        usersWithMostMoves.add(listEntry);
      }
    }
    return usersWithMostMoves;
  }
}
