package com.example.phase1activity.Domain.UserAccess;

import android.content.Context;

import com.example.phase1activity.Domain.Overseers.AppManager;
import com.example.phase1activity.Domain.Overseers.Profile;
import com.example.phase1activity.Infrastructure.AndroidSaver;
import com.example.phase1activity.Infrastructure.ISaver;

/**
 * Class that manages registration of new users
 */
public class RegisterManager extends SignupManager {

    /**
     * Function to sign up
     * @param context context of the app
     * @param username username you input
     * @param password password you input
     * @param app instance of the AppManager that is running
     * @return a string "valid combination" if the registration is successful
     */
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
