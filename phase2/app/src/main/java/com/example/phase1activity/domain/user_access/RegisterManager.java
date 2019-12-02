package com.example.phase1activity.domain.user_access;

import android.content.Context;
import android.graphics.Color;

import com.example.phase1activity.service.AppManager;
import com.example.phase1activity.service.ProfileBuilder;
import com.example.phase1activity.service.AndroidSaver;
import com.example.phase1activity.service.Saver;

/** A class that manages registration of new users. */
public class RegisterManager extends UserAccessManager {

  /**
   * If the inputted username and password are a valid combination, store a new user with the
   * specified username and password, and default values. Build a new profile with user attributes
   * that match the saved data, and set it as the appManager manager's profile.
   *
   * @param context context of the appManager.
   * @param username an inputted username.
   * @param password an inputted password.
   * @param app the AppManager that is running.
   * @return a Result enum that reflects whether the action was successful.
   */
  public Result UserAccessAction(
      Context context, String username, String password, AppManager app) {
    // Deal with invalid username-password combinations.
    Saver iSaver = new AndroidSaver(context);
    if (iSaver.getExistingUsernames().contains(username)) {
      return Result.TAKEN;
    } else if (!isValidUsername(username)) {
      return Result.ERROR_USERNAME;
    } else if (!isValidPassword(password)) {
      return Result.ERROR_PASSWORD;
    } else {
      // Save the new user's information with default values.
      final String DEFAULT_VALUES = "," + Color.RED + ",0,0,0,5,0";
      iSaver.saveData(username + "," + password + "," + username + DEFAULT_VALUES);
      app.setProfile(
          new ProfileBuilder()
              .setUsername(username)
              .setPassword(password)
              .setColour(Color.RED)
              .setNickname(username)
              .setGameLevel(0)
              .setSong(0)
              .setTotalScoreStat(0)
              .setTotalMovesStat(0)
              .setFastestRxnStat(5)
              .getProfile());
      return Result.VALID;
    }
  }
}
