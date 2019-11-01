package com.example.phase1activity.presentation.Register;

import android.content.Context;

import com.example.phase1activity.SaveAPI.AndroidSaver;
import com.example.phase1activity.SaveAPI.ISaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Handler;

// TODO: shouldn't have a public class here, and should use enums
public class RegisterManager {

    public String registerAction(Context context, String username, String password) {
        System.out.println("FILE DIRECTORY IN REGISTER MANAGER" + context.getFilesDir());
        ISaver iSaver = new AndroidSaver(context);
        if (iSaver.getExistingUsernames().contains(username)) {
            return "taken username";
        }
        else if(!isValidPassword(password)){
            return "empty password";
        }
        else if (!isValidUsername(username)) {
            return "username length error";
        }
        else{
            iSaver.saveData(username + "," + password + "," + "red," + username + ",song 1");
            return "valid combination";
        }
    }

    private boolean isValidUsername(String username){
        return username.length() > 0 && username.length() < 16;
    }

    private boolean isValidPassword(String password){
        return password.length() > 0;
    }
}
