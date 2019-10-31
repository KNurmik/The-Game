package com.example.phase1activity.SaveAPI;

import android.content.Context;

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
}
