package com.example.phase1activity.domain.user_access;

import android.content.Context;
import com.example.phase1activity.service.AppManager;
import com.example.phase1activity.service.ProfileBuilder;
import com.example.phase1activity.service.AndroidSaver;
import com.example.phase1activity.service.Saver;

import java.util.Map;
import java.util.Set;

/** A class to manage logging in. */
public class LogInManager extends UserAccessManager {

  /**
   * If there exists a user with the inputted username-password combination, build a new profile
   * with user attributes that match the saved data, and set it as the appManager manager's profile.
   *
   * @param context context of the appManager.
   * @param username an inputted username.
   * @param password an inputted password.
   * @param app the AppManager that is running.
   * @return a Result enum that reflects whether the action was successful.
   */
  public Result UserAccessAction(
      Context context, String username, String password, AppManager app) {
    // Deal with incorrect username-password combinations.
    if (!isValidPassword(password)) {
      return Result.ERROR_PASSWORD;
    } else if (!isValidUsername(username)) {
      return Result.ERROR_USERNAME;
    } else if (!isValidLogin(context, username, password)) {
      return Result.INCORRECT;
    } else {
      // Retrieve user's saved info.
      Saver iSaver = new AndroidSaver(context.getApplicationContext());
      Map<String, Map<AndroidSaver.AttributeType, String>> userData = iSaver.getExistingUserData();
      String nickname = userData.get(username).get(AndroidSaver.AttributeType.NICKNAME);
      int colour = Integer.parseInt(userData.get(username).get(AndroidSaver.AttributeType.COLOUR));
      int gameLevel =
          Integer.parseInt(userData.get(username).get(AndroidSaver.AttributeType.GAME_LEVEL));
      int song = Integer.parseInt(userData.get(username).get(AndroidSaver.AttributeType.SONG));
      int totalScoreStat =
          Integer.valueOf(userData.get(username).get(AndroidSaver.AttributeType.TOTAL_SCORE));
      double fastestRxnStat =
          Double.valueOf(userData.get(username).get(AndroidSaver.AttributeType.FASTEST_RXN_TIME));
      int totalMovesStat =
          Integer.valueOf(userData.get(username).get(AndroidSaver.AttributeType.TOTAL_MOVES));
      // Create a new profile containing the existing user's info, and set it as the AppManager's
      // current profile.
      app.setProfile(
          new ProfileBuilder()
              .setUsername(username)
              .setPassword(password)
              .setNickname(nickname)
              .setColour(colour)
              .setSong(song)
              .setGameLevel(gameLevel)
              .setFastestRxnStat(fastestRxnStat)
              .setTotalMovesStat(totalMovesStat)
              .setTotalScoreStat(totalScoreStat)
              .getProfile());
      return Result.VALID;
    }
  }

  /**
   * Return true if there exists a user with the username and password entered. Return false
   * otherwise.
   *
   * @param context the context.
   * @param username username of the user.
   * @param password password of the user.
   * @return turns a boolean whether the login was valid or not.
   */
  private boolean isValidLogin(Context context, String username, String password) {
    Saver iSaver = new AndroidSaver(context.getApplicationContext());
    Set<String> existingUsernames = iSaver.getExistingUsernames();

    if (existingUsernames.contains(username)) {
      Map<String, Map<AndroidSaver.AttributeType, String>> data = iSaver.getExistingUserData();
      String registeredPassword = data.get(username).get(AndroidSaver.AttributeType.PASSWORD);
      return password.equals(registeredPassword);
    } else {
      return false;
    }
  }
}
