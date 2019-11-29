package com.example.phase1activity.Core.Transmission.Overseers;

import android.app.Activity;
import android.app.Application;

import java.util.Map;

import javax.inject.Inject;

/** The App Manager. */
public class AppManager extends Application {
  @Inject GlobalStats globalStats;
  @Inject Music player;

  /** The profile the app is using. */
  private Profile profile;

  /** The level of difficulty the matching game is to played on. */
  private int matchingGameLevel;

  @Override
  public void onCreate() {

    // Dagger module for injecting.
    AppManagerModule module =
        new AppManagerModule(this, 1, "None", "None", "None", 0.0, 5.0, 100.0);

    // Inject player.
    player =
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

  public void changeMusic(int n) {
    player.changeMusic(this, n);
  }

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
   * Change the profile that the application is using.
   *
   * @param profile a profile.
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
   * Getter for the profile's username.
   *
   * @return this profile's username.
   */
  public String getProfileUsername() {
    return this.profile.getUsername();
  }

  /**
   * Change the song that is being played to song n.
   *
   * @param n the index of the song to play.
   */
  public void setProfileSong(Activity activity, int n) {
    this.profile.setSong(activity, n);
  }

  /**
   * Return the song that is being played.
   *
   * @return the song that is being played.
   */
  public int getProfileSong() {
    return this.profile.getSong();
  }

  /**
   * Return the game
   *
   * @return the song that is being played.
   */
  public int getProfileGameLevel() {
    return this.profile.getGameLevel();
  }

  public void setProfileGameLevel(Activity activity, int n) {
    this.profile.setGameLevel(activity, n);
  }

  /**
   * Setter for the the profile's fastest reaction time
   *
   * @param time The new time.
   */
  public void setProfileReactionTime(Activity activity, double time) {
    profile.setFastestRxnStat(activity, time);
  }

  /**
   * Increments the profile's total moves
   *
   * @param moves The amount of moves to increment profile.totalMovesStat
   */
  public void updateProfileMoves(Activity activity, int moves) {
    profile.incrementTotalMovesStat(activity, moves);
  }

  /**
   * Increments the profile's total score
   *
   * @param score The amount to increment profile.totalScoreStat
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
   * Setter for the profile's nickname
   *
   * @param name the nickname
   */
  public void setProfileNickname(Activity activity, String name) {
    this.profile.setNickname(activity, name);
  }

  public String getProfilePassword() {
    return this.profile.getPassword();
  }
  /** @return fastestRxnStat. */
  public double getProfileFastestRxnStat() {
    return profile.getFastestRxnStat();
  }

  /** @return totalScoreStat. */
  public int getProfileTotalScoreStat() {
    return profile.getTotalScoreStat();
  }

  /** @return totalMovesStat. */
  public int getProfileTotalMovesStat() {
    return profile.getTotalMovesStat();
  }

  public int getMatchingGameLevel() {
    return matchingGameLevel;
  }

  public void setMatchingGameLevel(int level) {
    matchingGameLevel = level;
  }
}
