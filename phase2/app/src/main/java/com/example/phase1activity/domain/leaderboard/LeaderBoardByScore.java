package com.example.phase1activity.domain.leaderboard;

import com.example.phase1activity.service.AndroidSaver;
import com.example.phase1activity.service.ISaver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/** Strategy for sorting users by most score. */
public class LeaderBoardByScore implements LeaderBoardSorting {

  /** Tell Dagger how to create this object. */
  @Inject
  public LeaderBoardByScore() {}

  /**
   * Sort players based on total score, return a list containing the players and their statistics.
   *
   * @param iSaver the ISaver object to read data from.
   * @return a sorted list of players and their statistics.
   */
  public List<List<Object>> sortPlayers(ISaver iSaver) {
    List<List<Object>> usersWithBestScores = new ArrayList<>();
    for (String username : iSaver.getHighScores().keySet()) {
      // Obtains the total score of the user in the across all games
      int userBestScore =
              iSaver
                      .getHighScores()
                      .get(username)
                      .get(AndroidSaver.AttributeType.TOTAL_SCORE)
                      .intValue();
      String nickname =
              iSaver.getExistingUserData().get(username).get(ISaver.AttributeType.NICKNAME);

      // A smaller list storing the user's nickname and their total moves.
      List<Object> listEntry = new ArrayList<>();
      listEntry.add(0, nickname);
      listEntry.add(1, userBestScore);

      boolean userAddedToList = false;

      // Inserts the list entry into the sorted list
      for (int i = 0; i < usersWithBestScores.size(); i++) {
        if (userBestScore > (int) usersWithBestScores.get(i).get(1)) {
          usersWithBestScores.add(i, listEntry);
          userAddedToList = true;
          break;
        }
      }
      // If the score is not greater than any other entries in the list so far.
      if (!userAddedToList) {
        usersWithBestScores.add(listEntry);
      }
    }
    return usersWithBestScores;
  }
}
