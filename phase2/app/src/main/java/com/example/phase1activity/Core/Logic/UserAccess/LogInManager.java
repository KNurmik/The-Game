package com.example.phase1activity.Core.Logic.UserAccess;

import android.content.Context;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.Core.Transmission.Overseers.ProfileBuilder;
import com.example.phase1activity.Core.Transmission.Saving.AndroidSaver;
import com.example.phase1activity.Core.Transmission.Saving.ISaver;

import java.util.HashMap;
import java.util.Set;

/** A class to manage logging in. */
public class LogInManager extends UserAccessManager {
  /**
   * @param context The current context
   * @param username username of the profile
   * @param password password of the profile
   * @param app the instance of AppManager
   * @return the string "valid login"
   */
  public String UserAccessAction(
      Context context, String username, String password, AppManager app) {
    // Deal with incorrect username-password combinations.
    if (!isValidPassword(password)) {
      return "empty password";
    } else if (!isValidUsername(username)) {
      return "empty username";
    }
    else if (!isValidLogin(context, username, password)) {
      return "incorrect username/password";
    } else {
      // Retrieve user's saved info.
      ISaver iSaver = new AndroidSaver(context.getApplicationContext());
      HashMap<String, HashMap<String, String>> userData = iSaver.getExistingUserData();
      String nickname = userData.get(username).get("nickname");
      int colour = Integer.parseInt(userData.get(username).get("colour"));
      int gameLevel = Integer.parseInt(userData.get(username).get("game level"));
      int song = Integer.parseInt(userData.get(username).get("song"));
      int totalScoreStat = Integer.valueOf(userData.get(username).get("total score"));
      double fastestRxnStat = Double.valueOf(userData.get(username).get("fastest reaction time"));
      int totalMovesStat = Integer.valueOf(userData.get(username).get("total moves"));
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
      return "valid login";
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
    ISaver iSaver = new AndroidSaver(context.getApplicationContext());
    Set<String> existingUsernames = iSaver.getExistingUsernames();

    if (existingUsernames.contains(username)) {
      HashMap<String, HashMap<String, String>> data = iSaver.getExistingUserData();
      String registeredPassword = data.get(username).get("password");
      return password.equals(registeredPassword);
    } else {
      return false;
    }
  }
}
