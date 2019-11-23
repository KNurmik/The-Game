package com.example.phase1activity.Core.Logic.UserAccess;

import android.content.Context;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.Core.Transmission.Overseers.ProfileBuilder;
import com.example.phase1activity.Core.Transmission.Saving.AndroidSaver;
import com.example.phase1activity.Core.Transmission.Saving.ISaver;

import java.util.HashMap;
import java.util.Set;

/**
 * A class to manage logins
 */
public class LogInManager extends UserAccessManager {
    /**
     *
     * @param context The current context
     * @param username username of the profile
     * @param password password of the profile
     * @param app the instance of AppManager
     * @return the string "valid login"
     */
     public String signupAction(Context context, String username, String password, AppManager app) {
        if (!isValidPassword(password)) {
            return "empty password";
        } else if (!isValidUsername(username)) {
            return "empty username";
        }
        // Deal with incorrect username-password combinations.
        else if (!isValidLogin(context, username, password)) {
            return "incorrect username/password";
        } else {
            ISaver iSaver = new AndroidSaver(context.getApplicationContext());
            HashMap<String, HashMap<String, String>> userData = iSaver.getExistingUserData();
            String nickname = userData.get(username).get("nickname");
            int colour = Integer.parseInt(userData.get(username).get("colour"));
            int gameLevel = Integer.parseInt(userData.get(username).get("game level"));
            int song = Integer.parseInt(userData.get(username).get("song"));
            int totalScoreStat = Integer.valueOf(userData.get(username).get("total score"));
            double fastestRxnStat = Double.valueOf(userData.get(username).get("fastest reaction time"));
            int totalMovesStat = Integer.valueOf(userData.get(username).get("total moves"));
            app.setProfile(new ProfileBuilder().setUsername(username).
                                                setPassword(password).
                                                setNickname(nickname).
                                                setColour(colour).
                                                setSong(song).
                                                setGameLevel(gameLevel).
                                                setFastestRxnStat(fastestRxnStat).
                                                setTotalMovesStat(totalMovesStat).
                                                setTotalScoreStat(totalScoreStat).
                                                getProfile());
            return "valid login";
        }
    }

    /**
     *
     * @param context The context of the function
     * @param username username of the user
     * @param password password of the user
     * @return turns a boolean whether the login was valid or not
     */
    private boolean isValidLogin(Context context, String username, String password) {
        ISaver iSaver = new AndroidSaver(context);

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
