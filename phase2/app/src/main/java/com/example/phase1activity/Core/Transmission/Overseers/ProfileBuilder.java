package com.example.phase1activity.Core.Transmission.Overseers;
// TODO: add desc.
public class ProfileBuilder {
  /** The user id. */
  private String username;

  /** The user's password. */
  private String password;

  /** The current level. */
  private int gameLevel;

  /** The user's colour. */
  private int colour;

  /** The song playing. */
  private int song;

  /** The user's nickname. */
  private String nickname;

  /** Fastest reaction time statistic (Reaction Game). */
  private double fastestRxnStat;

  /** The cumulative score for all three levels. */
  private int totalScoreStat;

  /** The cumulative number of moves for all three levels. */
  private int totalMovesStat;

  /** Return a new profile object with the attributes stored in this ProfileBuilder. */
  public Profile getProfile() {
    return new Profile(
        username,
        password,
        nickname,
        colour,
        gameLevel,
        song,
        totalScoreStat,
        fastestRxnStat,
        totalMovesStat);
  }

  /**
   * Set this ProfileBuilder's username to username. Return this ProfileBuilder.
   *
   * @param username a username.
   * @return this ProfileBuilder.
   */
  public ProfileBuilder setUsername(String username) {
    this.username = username;
    return this;
  }

  /**
   * Set this ProfileBuilder's password to password. Return this ProfileBuilder.
   *
   * @param password a password.
   * @return this ProfileBuilder.
   */
  public ProfileBuilder setPassword(String password) {
    this.password = password;
    return this;
  }

  /**
   * Set this ProfileBuilder's nickname to nickname. Return this ProfileBuilder.
   *
   * @param nickname a nickname.
   * @return this ProfileBuilder.
   */
  public ProfileBuilder setNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  /**
   * Set this ProfileBuilder's game level to gameLevel. Return this ProfileBuilder.
   *
   * @param gameLevel a gameLevel.
   * @return this ProfileBuilder.
   */
  public ProfileBuilder setGameLevel(int gameLevel) {
    this.gameLevel = gameLevel;
    return this;
  }

  /**
   * Set this ProfileBuilder's colour to colour. Return this ProfileBuilder.
   *
   * @param colour a colour.
   * @return this ProfileBuilder.
   */
  public ProfileBuilder setColour(int colour) {
    this.colour = colour;
    return this;
  }

  /**
   * Set this ProfileBuilder's fastest reaction stat to fastestRxnStat. Return this ProfileBuilder.
   *
   * @param fastestRxnStat a colour.
   * @return this ProfileBuilder.
   */
  public ProfileBuilder setFastestRxnStat(double fastestRxnStat) {
    this.fastestRxnStat = fastestRxnStat;
    return this;
  }

  /**
   * Set this ProfileBuilder's song to song. Return this ProfileBuilder.
   *
   * @param song a song index.
   * @return this ProfileBuilder.
   */
  public ProfileBuilder setSong(int song) {
    this.song = song;
    return this;
  }

  /**
   * Set this ProfileBuilder's total score stat to totalScoreStat. Return this ProfileBuilder.
   *
   * @param totalScoreStat a total score stat.
   * @return this ProfileBuilder.
   */
  public ProfileBuilder setTotalScoreStat(int totalScoreStat) {
    this.totalScoreStat = totalScoreStat;
    return this;
  }

  /**
   * Set this ProfileBuilder's total moves stat to totalMovesStat. Return this ProfileBuilder.
   *
   * @param totalMovesStat a total score stat.
   * @return this ProfileBuilder.
   */
  public ProfileBuilder setTotalMovesStat(int totalMovesStat) {
    this.totalMovesStat = totalMovesStat;
    return this;
  }
}
