package com.example.phase1activity.SaveAPI;

import android.content.Context;

interface ISaver {
    /**
     * Save contents from Context context.
     * @param contents the contents to be saved.
     * @param context the context the contents are to be saved from.
     */
    void saveData(String contents, Context context);

    /**
     * Load contents from Context context.
     * @param context the context the contents are to be loaded to.
     * @return previously saved contents.
     */
    String loadData(Context context);
}
