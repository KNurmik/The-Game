package com.example.phase1activity.presentation.LogIn;

import android.content.Context;

import com.example.phase1activity.Profile.AppManager;

public abstract class SignupManager {

    /**
     * The minimum and maximum lengths for text input
     */
    private final int MAX_ENTRY_LENGTH = 16;
    private final int MIN_ENTRY_LENGTH = 0;

    /**
     * Signing up, abstract method.
     */
    protected abstract String signupAction(Context context, String username, String password, AppManager app);

    /**
     * Checks if the given string is valid for a username
     * @param username The username that the user wants
     * @return true iff username is valid
     */
    protected boolean isValidUsername(String username) {
        return username.length() > MIN_ENTRY_LENGTH && username.length() < MAX_ENTRY_LENGTH && !username.contains(",");
    }

    /**
     * Checks if the given string is valid for a password
     * @param password The password that the user wants
     * @return true iff password is valid
     */
    protected boolean isValidPassword(String password) {
        return password.length() > MIN_ENTRY_LENGTH && password.length() < MAX_ENTRY_LENGTH && !password.contains(",");
    }
}
