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
   * @return a list of players and their statistics.
   */
  public List<List<Object>> sortPlayers(ISaver iSaver) {
    List<List<Object>> usersWithBestScores = new ArrayList<>();
    for (String username : iSaver.getHighScores().keySet()) {
      int userBestScore =
          iSaver.getHighScores().get(username).get(AndroidSaver.AttributeType.TOTAL_SCORE).intValue();
      String nickname = iSaver.getExistingUserData().get(username).get(ISaver.AttributeType.NICKNAME);
      List<Object> listEntry = new ArrayList<>();
      listEntry.add(0, nickname);
      listEntry.add(1, userBestScore);

      boolean userAddedToList = false;

      for (int i = 0; i < usersWithBestScores.size(); i++) {
        if (userBestScore > (int) usersWithBestScores.get(i).get(1)) {
          usersWithBestScores.add(i, listEntry);
          userAddedToList = true;
          break;
        }
      }
      if (!userAddedToList) {
        usersWithBestScores.add(listEntry);
      }
    }
    return usersWithBestScores;
  }
}
