package com.example.phase1activity.presentation.Register;

import android.content.Context;

import com.example.phase1activity.Profile.AppManager;
import com.example.phase1activity.Profile.Profile;
import com.example.phase1activity.SaveAPI.AndroidSaver;
import com.example.phase1activity.SaveAPI.ISaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Handler;

// TODO: shouldn't have a public class here, and should use enums
public class RegisterManager {
    private final int MAX_ENTRY_LENGTH = 16;
    final int MIN_ENTRY_LENGTH = 0;

    public String registerAction(Context context, String username, String password, AppManager app) {
        System.out.println("FILE DIRECTORY IN REGISTER MANAGER" + context.getFilesDir());
        ISaver iSaver = new AndroidSaver(context);
        if (iSaver.getExistingUsernames().contains(username)) {
            return "taken username";
        }
        else if(!isValidPassword(password)){
            return "password length error";
        }
        else if (!isValidUsername(username)) {
            return "username length error";
        }
        else{
            final String DEFAULT_VALUES = ",red,song 1,0,0,0,0";
            iSaver.saveData(username + "," + password + "," + username + DEFAULT_VALUES);
            app.setProfile(new Profile(username, password));
            return "valid combination";
        }
    }

    private boolean isValidUsername(String username){
        return username.length() > MIN_ENTRY_LENGTH && username.length() < MAX_ENTRY_LENGTH;
    }

    private boolean isValidPassword(String password){
        return password.length() > MIN_ENTRY_LENGTH && password.length() < MAX_ENTRY_LENGTH;
    }
}
