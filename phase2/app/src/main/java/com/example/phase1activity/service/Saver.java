package com.example.phase1activity.service;

import java.util.Map;
import java.util.Set;

/** An interface defining the methods needed for any game-saving system. */
public interface Saver {
  /**
   * Save contents.
   *
   * @param contents the contents to be saved.
   */
  void saveData(String contents);

  /**
   * Save a new profile with username and password, and default values.
   *
   * @param username a username.
   * @param password a password.
   */
  void saveNewProfile(String username, String password);

  /**
   * Load contents.
   *
   * @return previously saved contents.
   */
  String loadData();

  /**
   * Return a string array of user data, split by individual entries to the saving system.
   *
   * @return a string array of user data, split by individual entries to the saving system.
   */
  Map<String, Map<Saver.AttributeType, String>> getExistingUserData();

  /**
   * Return a map of usernames to a map of username attribute names to their objects.
   *
   * @return a map of usernames to a map of username attribute names to their objects.
   */
  Set<String> getExistingUsernames();

  void saveAttribute(String username, String newAttribute, Saver.AttributeType attributeType);

  /**
   * Return a map of usernames to a map of highest score types to their respective values for each
   * username.
   *
   * @return a map of usernames to a map of highest score types to their respective values for each
   *     username
   */
  Map<String, Map<Saver.AttributeType, Double>> getHighScores();

  enum AttributeType {
    PASSWORD,
    NICKNAME,
    COLOUR,
    SONG,
    GAME_LEVEL,
    FASTEST_RXN_TIME,
    TOTAL_SCORE,
    TOTAL_MOVES
  }
}
