package com.example.phase1activity.Domain;

import android.content.Context;

import com.example.phase1activity.Infrastructure.AndroidSaver;
import com.example.phase1activity.Infrastructure.ISaver;

public class RegisterManager extends SignupManager {

    public String signupAction(Context context, String username, String password, AppManager app) {
        System.out.println("FILE DIRECTORY IN REGISTER MANAGER" + context.getFilesDir());
        ISaver iSaver = new AndroidSaver(context);
        if (iSaver.getExistingUsernames().contains(username)) {
            return "taken username";
        } else if (!isValidPassword(password)) {
            return "password error";
        } else if (!isValidUsername(username)) {
            return "username error";
        } else {
            final String DEFAULT_VALUES = ",red,0,0,0,5,0";
            iSaver.saveData(username + "," + password + "," + username + DEFAULT_VALUES);
            app.setProfile(new Profile(username, password, username, "red", 0, 0, 0, 5, 0));
            return "valid combination";
        }
    }
}
