package com.example.phase1activity.Core.Logic.UserAccess;

import android.content.Context;
import android.graphics.Color;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.Core.Transmission.Overseers.ProfileBuilder;
import com.example.phase1activity.Core.Transmission.Saving.AndroidSaver;
import com.example.phase1activity.Core.Transmission.Saving.ISaver;

/** A class that manages registration of new users. */
public class RegisterManager extends UserAccessManager {

  /**
   * Function to sign up.
   *
   * @param context context of the app
   * @param username username you input
   * @param password password you input
   * @param app instance of the AppManager that is running
   * @return a string "valid combination" if the registration is successful
   */
  public Result UserAccessAction(
      Context context, String username, String password, AppManager app) {
    // Deal with invalid username-password combinations.
    ISaver iSaver = new AndroidSaver(context);
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
