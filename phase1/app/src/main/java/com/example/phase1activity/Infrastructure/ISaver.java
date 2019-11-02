package com.example.phase1activity.Infrastructure;

import java.util.HashMap;
import java.util.Set;

public interface ISaver {
    /**
     * Save contents.
     * @param contents the contents to be saved.
     */
    void saveData(String contents);

    /**
     * Load contents.
     * @return previously saved contents.
     */
    String loadData();

    HashMap<String, HashMap<String, String>> getExistingUserData();

    Set<String> getExistingUsernames();
}
