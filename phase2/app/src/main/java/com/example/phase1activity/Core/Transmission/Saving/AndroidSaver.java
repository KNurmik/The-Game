/*
 * Copyright 2019. Mark Vartolaş, Karl Hendrik Nurmeots, Olivia Li, Ramzi Dajani, Henry Leung.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



/* Code influenced by Week 6 Module WriteToFile.java. */

package com.example.phase1activity.Core.Transmission.Saving;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Attr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * A game saving mechanism specific to AndroidStudio.
 */
public class AndroidSaver implements ISaver {

    /**
     * The file to read and write data to.
     */
    private static final String SAVE_FILE = "userInfo.txt";

    /**
     * The tag used for logging information written and read.
     */
    private static final String TAG = "AndroidSaver";

    /**
     * The context data is to be written and read from.
     */
    private final Context context;

    private final int USERNAME_INDEX = 0;
    private final int PASSWORD_INDEX = 1;
    private final int NICKNAME_INDEX = 2;
    private final int COLOUR_INDEX = 3;
    private final int SONG_INDEX = 4;
    private final int GAME_LEVEL_INDEX = 5;
    private final int TOTAL_SCORE_INDEX = 6;
    private final int FASTEST_RXN_INDEX = 7;
    private final int TOTAL_MOVES_INDEX = 8;

    /**
     * Initialize this AndroidSaver.
     *
     * @param context the context of this AndroidSaver.
     */
    public AndroidSaver(Context context) {
        this.context = context;
        this.saveData("");
    }

    /**
     * Save contents to SAVE_FILE.
     *
     * @param contents the contents to be saved.
     */
    public void saveData(String contents) {
        PrintWriter out = null;

        try {
            OutputStream outStream = context.openFileOutput(SAVE_FILE, Context.MODE_APPEND);
            out = new PrintWriter(outStream);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Cannot save data: " + SAVE_FILE);
        }
        out.println(contents);
        out.close();
    }

    public void saveAttribute(String username, String newAttribute, AttributeType attributeType) {
        if (getExistingUsernames().contains(username)) {
            Map<AttributeType, String> userData = getExistingUserData().get(username);
            StringBuilder lineToSave = new StringBuilder();
            lineToSave.append(username + ",");

            List<AttributeType> attributeTypes = new ArrayList<AttributeType>();
            attributeTypes.add(AttributeType.PASSWORD);
            attributeTypes.add(AttributeType.NICKNAME);
            attributeTypes.add(AttributeType.COLOUR);
            attributeTypes.add(AttributeType.SONG);
            attributeTypes.add(AttributeType.GAME_LEVEL);
            attributeTypes.add(AttributeType.TOTAL_SCORE);
            attributeTypes.add(AttributeType.FASTEST_RXN_TIME);
            attributeTypes.add(AttributeType.TOTAL_MOVES);
            // TODO: introduce enums for attribute type
            for (AttributeType userAttr : attributeTypes) {
                if (userAttr.equals(attributeType)) {
                    lineToSave.append(newAttribute);
                } else {
                    lineToSave.append(userData.get(userAttr));
                }
                lineToSave.append(",");
            }
            // Remove the extra comma at the end of lineToSave, and save the line.
            saveData(String.valueOf(lineToSave).substring(0, lineToSave.length() - 1));
        }
    }

    /**
     * Load contents.
     *
     * @return previously saved contents.
     */
    public String loadData() {
        StringBuilder data = new StringBuilder();
        try (Scanner scanner = new Scanner(context.openFileInput(SAVE_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    data.append(line).append('\n');
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Cannot load data: " + SAVE_FILE);
        }

        return data.toString();
    }

    /**
     * Return a map of usernames to a map of highest score types to their respective values.
     *
     * @return a map of usernames to a map of highest score types to their respective values.
     */
    public Map<String, Map<AttributeType, Double>> getHighScores() {
        Map<String, Map<AttributeType, Double>> usernamesToHighScores = new HashMap<>();
        String[] splitByEntry = splitDataByEntry();

        for (String entry : splitByEntry) {
            final String[] splitByInfo = entry.split(",");

            String username = splitByInfo[USERNAME_INDEX];
            Double score = Double.valueOf(splitByInfo[TOTAL_SCORE_INDEX]);
            Double reaction = Double.valueOf(splitByInfo[FASTEST_RXN_INDEX]);
            Double moves = Double.valueOf(splitByInfo[TOTAL_MOVES_INDEX]);

            if (usernamesToHighScores.containsKey(username)) {
                Map<AttributeType, Double> bestStats = usernamesToHighScores.get(username);
                if (bestStats.get(AttributeType.TOTAL_SCORE) < score) {
                    bestStats.put(AttributeType.TOTAL_SCORE, score);
                }
                if (bestStats.get(AttributeType.FASTEST_RXN_TIME) > reaction) {
                    bestStats.put(AttributeType.FASTEST_RXN_TIME, reaction);
                }
                if (bestStats.get(AttributeType.TOTAL_MOVES) > moves) {
                    bestStats.put(AttributeType.TOTAL_MOVES, moves);
                }
            } else {
                Map<AttributeType, Double> usersFirstScoreEntry = new HashMap<>();
                usersFirstScoreEntry.put(AttributeType.TOTAL_SCORE, score);
                usersFirstScoreEntry.put(AttributeType.FASTEST_RXN_TIME, reaction);
                usersFirstScoreEntry.put(AttributeType.TOTAL_MOVES, moves);
                usernamesToHighScores.put(username, usersFirstScoreEntry);
            }
        }
        return usernamesToHighScores;
    }

    /**
     * Return the usernames that have been previously saved to this device.
     *
     * @return the usernames that have been previously saved to this device.
     */
    public Set<String> getExistingUsernames() {
        return getExistingUserData().keySet();
    }

    /**
     * Return a map of usernames to a map of username attribute names to their objects.
     *
     * @return a map of usernames to a map of username attribute names to their objects.
     */
    public Map<String, Map<AttributeType, String>> getExistingUserData() {
        Map<String, Map<AttributeType, String>> usernamesToData = new HashMap<>();

        String[] splitByEntry = splitDataByEntry();
        for (String entry : splitByEntry) {
            final String[] splitByInfo = entry.split(",");

            if (splitByInfo.length == 9) {
                Map<AttributeType, String> userData = new HashMap<AttributeType, String>() {{
                    put(AttributeType.PASSWORD, splitByInfo[PASSWORD_INDEX]);
                    put(AttributeType.NICKNAME, splitByInfo[NICKNAME_INDEX]);
                    put(AttributeType.COLOUR, splitByInfo[COLOUR_INDEX]);
                    put(AttributeType.SONG, splitByInfo[SONG_INDEX]);
                    put(AttributeType.GAME_LEVEL, splitByInfo[GAME_LEVEL_INDEX]);
                    put(AttributeType.TOTAL_SCORE, splitByInfo[TOTAL_SCORE_INDEX]);
                    put(AttributeType.FASTEST_RXN_TIME, splitByInfo[FASTEST_RXN_INDEX]);
                    put(AttributeType.TOTAL_MOVES, splitByInfo[TOTAL_MOVES_INDEX]);
                }};
                usernamesToData.put(splitByInfo[USERNAME_INDEX], userData);
            }
        }
        return usernamesToData;
    }

    /**
     * Return a string array of user data, split by individual entries to the saving system.
     *
     * @return a string array of user data, split by individual entries to the saving system.
     */
    private String[] splitDataByEntry() {
        String data = loadData();
        return data.split("\n");
    }

    public enum AttributeType {
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
