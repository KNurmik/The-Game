package com.example.phase1activity.service;

import android.app.Activity;
import android.app.Application;

import com.example.phase1activity.domain.leaderboard.GlobalStats;

import javax.inject.Inject;

/** A facade that oversees application processes. */
public class AppManagerImpl extends Application implements AppManager {

  /** An keeper of user stats. */
  @Inject GlobalStats globalStats;

  /** A player of music. */
  @Inject Music musicPlayer;

  /** The profile the appManager is using. */
  private Profile profile;

  /** The level of difficulty the matching game is to played on. */
  private int matchingGameLevel;

  /**
   * A boolean that returns true iff the last playthrough of the maze game was on easy mode, and
   * false otherwise.
   */
  private boolean easyMazeGame;

  @Override
  public void onCreate() {

    // Dagger module for injecting.
    AppManagerModule module =
        new AppManagerModule(this, 1, "None", "None", "None", 0.0, 5.0, 100.0);

    // Inject musicPlayer.
    musicPlayer =
        DaggerAppManagerComponent.builder()
            .appManagerModule(module)
            .build()
            .injectAppManagerPlayer();

    // Inject globalStats.
    globalStats =
        DaggerAppManagerComponent.builder()
            .appManagerModule(module)
            .build()
            .injectAppManagerGlobalStats();
    super.onCreate();
  }

  /**
   * Change the appManager's music to the song referenced by index n.
   *
   * @param n a song index.
   */
  public void changeMusic(int n) {
    musicPlayer.changeMusic(this, n);
  }

  /** Stop the appManager's music. */
  public void stopMusic() {
    musicPlayer.stopMusic();
  }

  /** Update user statistics in GlobalStats. */
  public void updateGlobalStats() {
    this.globalStats.updateGlobalStats();
  }

  /**
   * Getter for the profile that the application is using.
   *
   * @return the profile that the application is using.
   */
  public Profile getProfile() {
    return this.profile;
  }

  /**
   * Set this appManager manager's profile.
   *
   * @param profile a user profile.
   */
  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  /**
   * Getter for profile's selected colour
   *
   * @return profile.colour
   */
  public int getProfileColour() {
    return this.profile.getColour();
  }

  /**
   * Setter for profile's selected colour.
   *
   * @param color the color of the profile.
   */
  public void setProfileColour(Activity activity, int color) {
    this.profile.setColour(activity, color);
  }

  /**
   * Set the song preference of this appManager manager's profile to the song referenced by index n.
   *
   * @param activity the activity that the change is being made from.
   * @param n a song index.
   */
  public void setProfileSong(Activity activity, int n) {
    this.profile.setSong(activity, n);
  }

  /**
   * Get the index of the song preference of this appManager manger's profile.
   *
   * @return the index of the song preference of this appManager manager's profile.
   */
  public int getProfileSong() {
    return this.profile.getSong();
  }

  /**
   * Get the game level that this appManager manger's profile is playing through.
   *
   * @return the game level that this appManager manger's profile is playing through.
   */
  public int getProfileGameLevel() {
    return this.profile.getGameLevel();
  }

  /**
   * Set the game level that this appManager manger's profile is playing through.
   *
   * @param activity the activity that this change is made from.
   * @param n a game level.
   */
  public void setProfileGameLevel(Activity activity, int n) {
    this.profile.setGameLevel(activity, n);
  }

  /**
   * Set this appManager manager's profile's fastest reaction time.
   *
   * @param activity the activity that the change is being made from.
   * @param time an amount of time in seconds.
   */
  public void setProfileReactionTime(Activity activity, double time) {
    profile.setFastestRxnStat(activity, time);
  }

  /**
   * Increment this appManager manager's profile's moves stat.
   *
   * @param activity the activity that the change is being made from.
   * @param moves a number of moves.
   */
  public void updateProfileMoves(Activity activity, int moves) {
    profile.incrementTotalMovesStat(activity, moves);
  }

  /**
   * Increment this appManager manager's profile's moves stat.
   *
   * @param activity the activity that the change is being made from.
   * @param score a score.
   */
  public void updateProfileScore(Activity activity, int score) {
    profile.incrementTotalScoreStat(activity, score);
  }

  /**
   * Getter for the profile's nickname
   *
   * @return profile.nickname
   */
  public String getProfileNickname() {
    return profile.getNickname();
  }

  /**
   * Set the nickname of this appManager manager's profile.
   *
   * @param activity the activity this change is being made from.
   * @param name a nickname.
   */
  public void setProfileNickname(Activity activity, String name) {
    this.profile.setNickname(activity, name);
  }

  public String getProfilePassword() {
    return this.profile.getPassword();
  }

  /**
   * Get the fastest reaction stat of this appManager manager's profile.
   *
   * @return the fastest reaction stat of this appManager manager's profile.
   */
  public double getProfileFastestRxnStat() {
    return profile.getFastestRxnStat();
  }

  /**
   * Get the total score stat of this appManager manager's profile.
   *
   * @return the total score stat of this appManager manager's profile.
   */
  public int getProfileTotalScoreStat() {
    return profile.getTotalScoreStat();
  }

  /**
   * Get the total moves stat of this appManager manager's profile.
   *
   * @return the total moves stat of this appManager manager's profile.
   */
  public int getProfileTotalMovesStat() {
    return profile.getTotalMovesStat();
  }

  /**
   * Get the matching game level of this appManager manager's profile.
   *
   * @return the matching game level of this appManager manager's profile.
   */
  public int getMatchingGameLevel() {
    return matchingGameLevel;
  }

  /**
   * Set the matching game level of this appManager manager's profile.
   *
   * @param level a level index.
   */
  public void setMatchingGameLevel(int level) {
    matchingGameLevel = level;
  }

  /**
   * Get the maze game difficulty of this appManager manager's profile during the user's last
   * playthrough.
   *
   * @return the maze game difficulty of this appManager manager's profile during the user's last
   *     playthrough.
   */
  public boolean getMazeGameDifficulty() {
    return easyMazeGame;
  }

  /**
   * Set the maze game difficulty of this appManager manager's profile.
   *
   * @param difficulty a boolean evaluating to true iff the maze game is to be set to easy
   *     difficulty.
   */
  public void setMazeGameDifficulty(boolean difficulty) {
    easyMazeGame = difficulty;
  }

  public int getUserLevel() {
    return profile.getUserLevel();
  }
}
