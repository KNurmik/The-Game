package com.example.phase1activity.Core.Logic.UserAccess;

import android.content.Context;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;

/** Responsible for logic computations for UserAccess. Used by UserAccessPresenter. */
public abstract class UserAccessManager {

  /** The maximum length for username/password input. */
  private final int MAX_ENTRY_LENGTH = 9;

  /** The minimum length for username/password input. */
  private final int MIN_ENTRY_LENGTH = 0;

  /**
   * Handle a user's attempt to login/register.
   *
   * @param context context of the app.
   * @param username an inputted username.
   * @param password an inputted password.
   * @param app the AppManager that is running.
   * @return a Result enum that reflects whether the action was successful.
   */
  public abstract Result UserAccessAction(
      Context context, String username, String password, AppManager app);

  /**
   * Checks if the given string is valid for a username
   *
   * @param username the username that the user wants
   * @return true iff username is valid
   */
  boolean isValidUsername(String username) {
    return username.length() > MIN_ENTRY_LENGTH
        && username.length() < MAX_ENTRY_LENGTH
        && !username.contains(",");
  }

  /**
   * Check whether the given string is valid for a password.
   *
   * @param password the password that the user wants.
   * @return true iff password is valid.
   */
  boolean isValidPassword(String password) {
    return password.length() > MIN_ENTRY_LENGTH
        && password.length() < MAX_ENTRY_LENGTH
        && !password.contains(",");
  }

  /** A representation of the result of some user access attempt. */
  public enum Result {
    INCORRECT,
    TAKEN,
    ERROR_USERNAME,
    ERROR_PASSWORD,
    VALID
  }
}
