package com.example.phase1activity.service;

import android.app.Activity;

/** A facade that oversees application processes. */
public interface AppManagerInterface {

  /**
   * Change the appManager's music to the song referenced by index n.
   *
   * @param n a song index.
   */
  void changeMusic(int n);

  /** Stop the appManager's music. */
  void stopMusic();

  /** Update user statistics in GlobalStats. */
  void updateGlobalStats();

  /**
   * Getter for the profile that the application is using.
   *
   * @return the profile that the application is using.
   */
  Profile getProfile();

  /**
   * Set this appManager manager's profile.
   *
   * @param profile a user profile.
   */
  void setProfile(Profile profile);

  /**
   * Get the colour preference of this appManager manager's profile.
   *
   * @return an int representing colour preference of this appManager manager's profile.
   */
  int getProfileColour();

  /**
   * Set the colour preference of thia appManager manager's profile to color.
   *
   * @param activity the activity that the change is being made from.
   * @param color a color.
   */
  void setProfileColour(Activity activity, int color);

  /**
   * Set the song preference of this appManager manager's profile to the song referenced by index n.
   *
   * @param activity the activity that the change is being made from.
   * @param n a song index.
   */
  void setProfileSong(Activity activity, int n);

  /**
   * Get the index of the song preference of this appManager manger's profile.
   *
   * @return the index of the song preference of this appManager manager's profile.
   */
  int getProfileSong();

  /**
   * Get the game level that this appManager manger's profile is playing through.
   *
   * @return the game level that this appManager manger's profile is playing through.
   */
  int getProfileGameLevel();

  /**
   * Set the game level that this appManager manger's profile is playing through.
   *
   * @param activity the activity that this change is made from.
   * @param n a game level.
   */
  public void setProfileGameLevel(Activity activity, int n);

  /**
   * Set this appManager manager's profile's fastest reaction time.
   *
   * @param activity the activity that the change is being made from.
   * @param time an amount of time in seconds.
   */
  void setProfileReactionTime(Activity activity, double time);

  /**
   * Increment this appManager manager's profile's moves stat.
   *
   * @param activity the activity that the change is being made from.
   * @param moves a number of moves.
   */
  void updateProfileMoves(Activity activity, int moves);

  /**
   * Increment this appManager manager's profile's moves stat.
   *
   * @param activity the activity that the change is being made from.
   * @param score a score.
   */
  void updateProfileScore(Activity activity, int score);

  /**
   * Get the nickname of this appManager manager's profile.
   *
   * @return the nickname of this appManager manager's profile.
   */
  String getProfileNickname();

  /**
   * Setter for the profile's nickname
   *
   * @param name the nickname
   */
  void setProfileNickname(Activity activity, String name);

  /**
   * Get the fastest reaction stat of this appManager manager's profile.
   *
   * @return the fastest reaction stat of this appManager manager's profile.
   */
  double getProfileFastestRxnStat();

  /**
   * Get the total score stat of this appManager manager's profile.
   *
   * @return the total score stat of this appManager manager's profile.
   */
  int getProfileTotalScoreStat();

  /**
   * Get the total moves stat of this appManager manager's profile.
   *
   * @return the total moves stat of this appManager manager's profile.
   */
  int getProfileTotalMovesStat();

  /**
   * Get the user level of this appManager manager's profile.
   *
   * @return the user level of this appManager manager's profile.
   */
  int getUserLevel();

  /**
   * Get the matching game level of this appManager manager's profile.
   *
   * @return the matching game level of this appManager manager's profile.
   */
  int getMatchingGameLevel();

  /**
   * Set the matching game level of this appManager manager's profile.
   *
   * @param level a level index.
   */
  void setMatchingGameLevel(int level);

  /**
   * Get the maze game difficulty of this appManager manager's profile during the user's last playthrough.
   *
   * @return the maze game difficulty of this appManager manager's profile during the user's last
   *     playthrough.
   */
  boolean getMazeGameDifficulty();

  /**
   * Set the maze game difficulty of this appManager manager's profile.
   *
   * @param difficulty a boolean evaluating to true iff the maze game is to be set to easy
   *     difficulty.
   */
  void setMazeGameDifficulty(boolean difficulty);
}
