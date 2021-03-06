package com.example.phase1activity.domain.leaderboard;

import com.example.phase1activity.service.AndroidSaver;
import com.example.phase1activity.service.Saver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/** Strategy for sorting players based on their fastest reaction time. */
public class LeaderBoardByReactionTime implements LeaderBoardSorting {

  /** Tell Dagger how to create this object. */
  @Inject
  public LeaderBoardByReactionTime() {}

  /**
   * Sort players based on their fastest reaction time, return a list of players and their
   * statistic.
   *
   * @param saver the saver to read the data from.
   * @return a list of players along with their statistics.
   */
  public List<List<Object>> sortPlayers(Saver saver) {
    List<List<Object>> usersWithFastestReactions = new ArrayList<>();
    for (String username : saver.getHighScores().keySet()) {
      // Obtains the fastest reaction time by the user in the reaction game
      double userFastestReaction =
          saver.getHighScores().get(username).get(AndroidSaver.AttributeType.FASTEST_RXN_TIME);
      String nickname = saver.getExistingUserData().get(username).get(Saver.AttributeType.NICKNAME);

      // A smaller list storing the user's nickname and their fastest reaction time.
      List<Object> listEntry = new ArrayList<>();
      listEntry.add(0, nickname);
      listEntry.add(1, userFastestReaction);

      boolean userAddedToList = false;

      // Inserts the list entry into the sorted list
      for (int i = 0; i < usersWithFastestReactions.size(); i++) {
        if (userFastestReaction < (double) usersWithFastestReactions.get(i).get(1)) {
          usersWithFastestReactions.add(i, listEntry);
          userAddedToList = true;
          break;
        }
      }
      if (!userAddedToList) {
        usersWithFastestReactions.add(listEntry);
      }
    }
    return usersWithFastestReactions;
  }
}
