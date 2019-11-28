package com.example.phase1activity.Core.Transmission.Saving;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An interface defining the methods needed for any game-saving mechanism (no matter the OS it is
 * specific to.
 */
public interface ISaver {
    /**
     * Save contents.
     *
     * @param contents the contents to be saved.
     */
    void saveData(String contents);

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
    Map<String, Map<AndroidSaver.AttributeType, String>> getExistingUserData();

    /**
     * Return a map of usernames to a map of username attribute names to their objects.
     *
     * @return a map of usernames to a map of username attribute names to their objects.
     */
    Set<String> getExistingUsernames();

    void saveAttribute(String username, String newAttribute, AndroidSaver.AttributeType attributeType);

    /**
     * Return a map of usernames to a map of highest score types to their respective values.
     *
     * @return a map of usernames to a map of highest score types to their respective values.
     */
    Map<String, Map<AndroidSaver.AttributeType, Double>> getHighScores();
}
