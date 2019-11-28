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

  public Map<String, Double> getBestTotal() {
    return this.globalStats.getBestTotal();
  }

  public Map<String, Double> getBestReaction() {
    return this.globalStats.getBestReaction();
  }

  public Map<String, Double> getBestMoves() {
    return this.globalStats.getBestMoves();
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
  public void setProfileColour(int color) {
    this.profile.setColour(color);
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
  public void setProfileSong(int n) {
    this.profile.setSong(n);
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
  public void setProfileReactionTime(double time) {
    profile.setFastestRxnStat(time);
  }

  /**
   * Increments the profile's total moves
   *
   * @param moves The amount of moves to increment profile.totalMovesStat
   */
  public void updateProfileMoves(int moves) {
    profile.updateTotalMovesStat(moves);
  }

  /**
   * Increments the profile's total score
   *
   * @param score The amount to increment profile.totalScoreStat
   */
  public void updateProfileScore(int score) {
    profile.updateTotalScoreStat(score);
  }

  /** Resets the profile's total score statistic */
  public void resetProfileScore() {
    profile.resetTotalScoreStat();
  }

  /** Resets the profile's total moves statistic */
  public void resetProfileMoves() {
    profile.resetTotalMovesStat();
  }

  /** Resets the profile's fastest reaction time statistic */
  public void resetProfileRxnStat() {
    profile.resetFastestRxnStat();
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
  public void setProfileNickname(String name) {
    this.profile.setNickname(name);
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
