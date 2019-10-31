package com.example.phase1activity.SaveAPI;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class AndroidSaver implements ISaver {

    /** The file to read and write data to. */
    private static final String SAVE_FILE = "userInfo.txt";

    /** The tag used for logging information written and read. */
    private static final String TAG = "AndroidSave";

    /**
     * Save contents from Context context to SAVE_FILE.
     * @param contents the contents to be saved.
     * @param context the context the contents are to be saved from.
     */
    public void saveData(String contents, Context context) {
        PrintWriter out = null;

        try {
            OutputStream outStream = context.openFileOutput(SAVE_FILE, Context.MODE_PRIVATE);
            out = new PrintWriter(outStream);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Cannot save data: " + SAVE_FILE);
        }

        out.println(contents);
        out.close();
    }

    /**
     * Load contents from Context context.
     * @param context the context the contents are to be loaded to.
     * @return previously saved contents.
     */
    public String loadData(Context context) {
        StringBuilder data = new StringBuilder();
        try (Scanner scanner = new Scanner(context.openFileInput(SAVE_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e(TAG, "Cannot load data: " + SAVE_FILE);
        }

        return data.toString();
    }
}
