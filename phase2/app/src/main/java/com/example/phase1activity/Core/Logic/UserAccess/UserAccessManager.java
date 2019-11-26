package com.example.phase1activity.Core.Logic.UserAccess;

import android.content.Context;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;

/** Responsible for logic computations for UserAccess. Used by UserAccessPresenter. */
public abstract class UserAccessManager {

  /** The minimum and maximum lengths for text input */
  private final int MAX_ENTRY_LENGTH = 16;

  private final int MIN_ENTRY_LENGTH = 0;

  /** User is attempting to login/register using entered username and password, handle this attempt.*/
  public abstract Result UserAccessAction(
      Context context, String username, String password, AppManager app);

  /**
   * Checks if the given string is valid for a username
   *
   * @param username The username that the user wants
   * @return true iff username is valid
   */
  boolean isValidUsername(String username) {
    return username.length() > MIN_ENTRY_LENGTH
        && username.length() < MAX_ENTRY_LENGTH
        && !username.contains(",");
  }

  /**
   * Checks if the given string is valid for a password
   *
   * @param password The password that the user wants
   * @return true iff password is valid
   */
  boolean isValidPassword(String password) {
    return password.length() > MIN_ENTRY_LENGTH
        && password.length() < MAX_ENTRY_LENGTH
        && !password.contains(",");
  }

  public enum Result {
    INCORRECT,
    TAKEN,
    ERROR_USERNAME,
    ERROR_PASSWORD,
    VALID
  }
}
