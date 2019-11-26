/* Code influenced by Week 6 Module WriteToFile.java. */

package com.example.phase1activity.Core.Transmission.Saving;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
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

    public void saveAttribute(String username, String newAttribute, String attributeType) {
        if (getExistingUsernames().contains(username)) {
            HashMap<String, String> userData = getExistingUserData().get(username);
            StringBuilder lineToSave = new StringBuilder();
            lineToSave.append(username + ",");

            String[] attributeTypes = {"password", "nickname", "colour", "song", "game level",
                                       "total score", "fastest reaction time", "total moves"};

            // TODO: introduce enums for attribute type
            for (String userAttr : attributeTypes) {
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
    public Map<String, Map<String, Double>> getHighScores() {
        Map<String, Map<String, Double>> usernamesToHighScores = new HashMap<>();
        String[] splitByEntry = splitDataByEntry();

        for (String entry : splitByEntry) {
            final String[] splitByInfo = entry.split(",");

            String username = splitByInfo[USERNAME_INDEX];
            Double score = Double.valueOf(splitByInfo[TOTAL_SCORE_INDEX]);
            Double reaction = Double.valueOf(splitByInfo[FASTEST_RXN_INDEX]);
            Double moves = Double.valueOf(splitByInfo[TOTAL_MOVES_INDEX]);

            if (usernamesToHighScores.containsKey(username)) {
                Map<String, Double> bestStats = usernamesToHighScores.get(username);
                if (bestStats.get("total score") < score) {
                    bestStats.put("total score", score);
                }
                if (bestStats.get("fastest reaction") > reaction) {
                    bestStats.put("fastest reaction", reaction);
                }
                if (bestStats.get("total moves") > moves) {
                    bestStats.put("total moves", moves);
                }
            } else {
                Map<String, Double> usersFirstScoreEntry = new HashMap<>();
                usersFirstScoreEntry.put("total score", score);
                usersFirstScoreEntry.put("fastest reaction", reaction);
                usersFirstScoreEntry.put("total moves", moves);
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
    public Map<String, HashMap<String, String>> getExistingUserData() {
        Map<String, HashMap<String, String>> usernamesToData = new HashMap<>();

        String[] splitByEntry = splitDataByEntry();
        for (String entry : splitByEntry) {
            final String[] splitByInfo = entry.split(",");

            if (splitByInfo.length == 9) {
                HashMap<String, String> userData = new HashMap<String, String>() {{
                    put("password", splitByInfo[PASSWORD_INDEX]);
                    put("nickname", splitByInfo[NICKNAME_INDEX]);
                    put("colour", splitByInfo[COLOUR_INDEX]);
                    put("song", splitByInfo[SONG_INDEX]);
                    put("game level", splitByInfo[GAME_LEVEL_INDEX]);
                    put("total score", splitByInfo[TOTAL_SCORE_INDEX]);
                    put("fastest reaction time", splitByInfo[FASTEST_RXN_INDEX]);
                    put("total moves", splitByInfo[TOTAL_MOVES_INDEX]);
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
}
