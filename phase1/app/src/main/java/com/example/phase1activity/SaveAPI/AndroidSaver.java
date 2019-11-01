package com.example.phase1activity.SaveAPI;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

// TODO: class should not be public.
public class AndroidSaver implements ISaver {

    /** The file to read and write data to. */
    private static final String SAVE_FILE = "userInfo.txt";

    /** The tag used for logging information written and read. */
    private static final String TAG = "AndroidSaver";

    /** The context data is to be written and read from. */
    private final Context context;

    public AndroidSaver(Context context) {
        this.context = context;
        this.saveData("");
    }

    /**
     * Save contents to SAVE_FILE.
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

    /**
     * Load contents.
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

    // TODO: should not be public
    public Set<String> getExistingUsernames() {
        return getExistingUserData().keySet();
    }

    public HashMap<String, HashMap<String, String>> getExistingUserData() {
        final int USERNAME_INDEX = 0;
        final int PASSWORD_INDEX = 1;
        final int COLOUR_INDEX = 2;
        final int NICKNAME_INDEX = 3;
        final int SONG_INDEX = 4;

        HashMap<String, HashMap<String, String>> usernamesToData = new HashMap<>();

        String[] splitByEntry = splitDataByEntry();
        for (String entry : splitByEntry) {
            final String[] splitByInfo = entry.split(",");

            if (splitByInfo.length == 5) {
                HashMap<String, String> userData = new HashMap<String, String>() {{
                    put("password", splitByInfo[PASSWORD_INDEX]);
                    put("color", splitByInfo[COLOUR_INDEX]);
                    put("nickname", splitByInfo[NICKNAME_INDEX]);
                    put("song", splitByInfo[SONG_INDEX]);
                }};
                usernamesToData.put(splitByInfo[USERNAME_INDEX], userData);
            }
        }
        return usernamesToData;
    }

    private String[] splitDataByEntry() {
        String data = loadData();
        return data.split("\n");
    }
}
