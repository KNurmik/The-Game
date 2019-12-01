package com.example.phase1activity.service;

import android.app.Activity;
import android.graphics.Color;

// TODO: add desc.
class Profile {
  /** The user's username. */
  private String username;

  /** The user's password. */
  private String password;

  /** The current/last-played level. */
  private int gameLevel;

  /** The color of various objects in the levels. */
  private int colour;

  /** The song to be played. */
  private int song;

  /** The user's nickname, set to username as default. */
  private String nickname;

  /** Fastest reaction time statistic (Reaction Game). */
  private double fastestRxnStat;

  /** The cumulative score for all three levels. */
  private int totalScoreStat;

  /** The cumulative number of moves for all three levels. */
  private int totalMovesStat;

  Profile(
      String username,
      String password,
      String nickname,
      int colour,
      int gameLevel,
      int song,
      int totalScore,
      double fastestReactionTime,
      int totalMoves) {
    this.username = username;
    this.password = password;
    this.nickname = nickname;
    this.colour = colour;
    this.gameLevel = gameLevel;
    this.song = song;
    this.fastestRxnStat = fastestReactionTime;
    this.totalScoreStat = totalScore;
    this.totalMovesStat = totalMoves;
  }

  /**
   * Getter for the user's username.
   *
   * @return the user's username.
   */
  String getUsername() {
    return this.username;
  }

  /**
   * Set the color preference of the user.
   *
   * @param colour a colour.
   */
  void setColour(Activity activity, int colour) {
    ISaver iSaver = new AndroidSaver(activity);
    this.colour = colour;
    iSaver.saveAttribute(
            username, String.valueOf(colour), AndroidSaver.AttributeType.COLOUR);
  }

  /**
   * Returns the user's colour preference.
   *
   * @return the user's colour preference.
   */
  int getColour() {
    if (colour == Color.RED) {
      return Color.RED;
    } else if (colour == Color.BLUE) {
      return Color.BLUE;
    } else {
      return Color.GREEN;
    }
  }

  /**
   * Return the current level the user is playing.
   *
   * @return an integer representation of the current level.
   */
  int getGameLevel() {
    return this.gameLevel;
  }

  /**
   * Set the current level that user is playing.
   *
   * @param activity the activity the data is saved from.
   * @param n a level.
   */
  void setGameLevel(Activity activity, int n) {
    ISaver iSaver = new AndroidSaver(activity);
    this.gameLevel = n;
    iSaver.saveAttribute(
        username, String.valueOf(gameLevel), AndroidSaver.AttributeType.GAME_LEVEL);
  }

  /**
   * Getter for the user's nickname
   *
   * @return String representation of the user's nickname
   */
  String getNickname() {
    return this.nickname;
  }

  /**
   * Gets the song that is currently playing
   *
   * @return the numerical id of the song being played
   */
  int getSong() {
    return this.song;
  }

  /**
   * Set the user's nickname to nickname.
   *
   * @param nickname a nickname.
   */
  void setNickname(Activity activity, String nickname) {
    ISaver iSaver = new AndroidSaver(activity);
    this.nickname = nickname;
    iSaver.saveAttribute(
            username, nickname, AndroidSaver.AttributeType.NICKNAME);
  }

  /**
   * Set this profile's background music to the song at newSongIndex.
   *
   * @param activity the activity the song is saved from.
   * @param newSongIndex the index of a song.
   */
  void setSong(Activity activity, int newSongIndex) {
    ISaver iSaver = new AndroidSaver(activity);
    this.song = newSongIndex;
    iSaver.saveAttribute(
            username, String.valueOf(newSongIndex), AndroidSaver.AttributeType.SONG);
  }

  /**
   * Set this profile's fastest reaction time statistic to newStat, if newStat is less than the
   * profile's current fastest reaction time.
   *
   * @param activity the activity the stat is saved from.
   * @param newStat an amount of time.
   */
  void setFastestRxnStat(Activity activity, double newStat) {
    if (this.fastestRxnStat > newStat) {
      ISaver iSaver = new AndroidSaver(activity);
      this.fastestRxnStat = newStat;
      iSaver.saveAttribute(
              username, String.valueOf(fastestRxnStat), AndroidSaver.AttributeType.FASTEST_RXN_TIME);
    }
  }

  /**
   * Increment this profile's total moves statistic by newStat.
   *
   * @param activity the activity the stat is saved from.
   * @param moves a number of moves.
   */
  void incrementTotalMovesStat(Activity activity, int moves) {
    ISaver iSaver = new AndroidSaver(activity);
    this.totalMovesStat += moves;
    iSaver.saveAttribute(
            username, String.valueOf(totalMovesStat), AndroidSaver.AttributeType.TOTAL_MOVES);
  }

  /**
   * Increment this profile's total score statistic by newStat.
   *
   * @param activity the activity the stat is saved from.
   * @param score a score.
   */
  void incrementTotalScoreStat(Activity activity, int score) {
    ISaver iSaver = new AndroidSaver(activity);
    this.totalScoreStat += score;
    iSaver.saveAttribute(
            username, String.valueOf(totalScoreStat), AndroidSaver.AttributeType.TOTAL_SCORE);
  }

//  /** Reset totalScoreStat to 0. */
//  void resetTotalScoreStat() {
//    totalScoreStat = 0;
//  }
//
//  /** Reset totalMovesStat to 0. */
//  void resetTotalMovesStat() {
//    totalMovesStat = 0;
//  }
//
//  /** Reset fastestRxnStat to 5. */
//  void resetFastestRxnStat() {
//    fastestRxnStat = 5;
//  }

  /** @return fastestRxnStat. */
  double getFastestRxnStat() {
    return this.fastestRxnStat;
  }

  /** @return totalScoreStat. */
  int getTotalScoreStat() {
    return this.totalScoreStat;
  }

  /** @return totalMovesStat. */
  int getTotalMovesStat() {
    return this.totalMovesStat;
  }

  /** @return password. */
  String getPassword() {
    return this.password;
  }

  /** @return the user's level. */
  int getUserLevel() {
    int userLevel = 0;

    if (totalScoreStat >= 10000000) {
      userLevel += 3;
    } else if (totalScoreStat >= 100000) {
      userLevel += 2;
    } else if (totalScoreStat >= 10000) {
      userLevel += 1;
    }

    if (totalMovesStat >= 10000) {
      userLevel += 3;
    } else if (totalMovesStat >= 1000) {
      userLevel += 2;
    } else if (totalMovesStat >= 100) {
      userLevel += 1;
    }

    if (fastestRxnStat < 0.05) {
      userLevel += 3;
    } else if (fastestRxnStat <  0.3) {
      userLevel += 2;
    } else if (fastestRxnStat < 0.4) {
      userLevel += 1;
    }

    return userLevel;
  }
}
