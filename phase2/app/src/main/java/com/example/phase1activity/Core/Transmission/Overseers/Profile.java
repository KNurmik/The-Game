package com.example.phase1activity.Core.Transmission.Overseers;

import android.app.Activity;
import android.graphics.Color;

import com.example.phase1activity.Core.Transmission.Saving.AndroidSaver;
import com.example.phase1activity.Core.Transmission.Saving.ISaver;

class Profile {
  /** Thhe user's username. */
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
  void setColour(int colour) {
    this.colour = colour;
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
   * Sets the user's nickname to nickname.
   *
   * @param nickname the profile's new nickname.
   */
  void setNickname(String nickname) {
    this.nickname = nickname;
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
   * Set for the current level the user is playing.
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

  /** Changes the song the app plays while open */
  void setSong(int n) {
    this.song = n;
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
   * Sets the Fastest reaction time statistic
   *
   * @param time the fastest time a user reacts in the Reaction Game.
   */
  void setFastestRxnStat(double time) {
    this.fastestRxnStat = time;
  }

  /**
   * Increments total moves statistic
   *
   * @param moves totalMovesStat is incremented by moves
   */
  void updateTotalMovesStat(int moves) {
    this.totalMovesStat += moves;
  }

  /**
   * Increments total score statistic
   *
   * @param score totalScoreStat is incremented by score
   */
  void updateTotalScoreStat(int score) {
    this.totalScoreStat += score;
  }

  /** Reset totalScoreStat to 0. */
  void resetTotalScoreStat() {
    totalScoreStat = 0;
  }

  /** Reset totalMovesStat to 0. */
  void resetTotalMovesStat() {
    totalMovesStat = 0;
  }

  /** Reset fastestRxnStat to 5. */
  void resetFastestRxnStat() {
    fastestRxnStat = 5;
  }

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
}
