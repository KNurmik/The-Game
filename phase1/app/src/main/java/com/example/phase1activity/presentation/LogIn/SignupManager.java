package com.example.phase1activity.presentation.LogIn;

import android.content.Context;

import com.example.phase1activity.Profile.AppManager;

public abstract class SignupManager {

    private final int MAX_ENTRY_LENGTH = 16;
    private final int MIN_ENTRY_LENGTH = 0;

    protected abstract String signupAction(Context context, String username, String password, AppManager app);

    protected boolean isValidUsername(String username) {
        return username.length() > MIN_ENTRY_LENGTH && username.length() < MAX_ENTRY_LENGTH && !username.contains(",");
    }

    protected boolean isValidPassword(String password) {
        return password.length() > MIN_ENTRY_LENGTH && password.length() < MAX_ENTRY_LENGTH && !password.contains(",");
    }
}
