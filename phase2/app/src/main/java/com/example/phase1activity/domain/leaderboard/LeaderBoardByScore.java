package com.example.phase1activity.domain.leaderboard;

import com.example.phase1activity.service.AndroidSaver;
import com.example.phase1activity.service.SaverInterface;

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
   * @param saver the SaverInterface object to read data from.
   * @return a list of players and their statistics.
   */
  public List<List<Object>> sortPlayers(SaverInterface saver) {
    List<List<Object>> usersWithBestScores = new ArrayList<>();
    for (String username : saver.getHighScores().keySet()) {
      int userBestScore =
              saver.getHighScores().get(username).get(AndroidSaver.AttributeType.TOTAL_SCORE).intValue();
      String nickname = saver.getExistingUserData().get(username).get(SaverInterface.AttributeType.NICKNAME);
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
