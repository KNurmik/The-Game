package com.example.phase1activity.Domain;

import android.content.Context;

import com.example.phase1activity.Infrastructure.AndroidSaver;
import com.example.phase1activity.Infrastructure.ISaver;

import java.util.HashMap;
import java.util.Set;

public class LogInManager extends SignupManager {

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
            String colour = userData.get(username).get("colour");
            int gameLevel = Integer.parseInt(userData.get(username).get("game level"));
            int song = Integer.parseInt(userData.get(username).get("song"));
            int totalScoreStat = Integer.valueOf(userData.get(username).get("total score"));
            double fastestRxnStat = Double.valueOf(userData.get(username).get("fastest reaction time"));
            int totalMovesStat = Integer.valueOf(userData.get(username).get("total moves"));
            app.setProfile(new Profile(username, password, nickname, colour, gameLevel, song, totalScoreStat, fastestRxnStat, totalMovesStat));
            return "valid login";
        }
    }

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
