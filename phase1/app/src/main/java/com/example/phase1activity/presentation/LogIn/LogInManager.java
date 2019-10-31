package com.example.phase1activity.presentation.LogIn;

import com.example.phase1activity.Profile.AppManager;
import com.example.phase1activity.Profile.Profile;


public class LogInManager implements LogInInterface {
    private String username;
    private String password;

    LogInManager(){
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }


    public String logInAction(String user, String pass, AppManager app){
        setUsername(user);
        setPassword(pass);

        if(!isValidPassword()){
            return "empty password";
        }
        else if(!isValidUsername()){
            return "empty username";
        }

        // Deal with incorrect username-password combinations.
        else if(!isValidLogin()){
            return "incorrect username/password";
        }

        else{
            app.setProfile(new Profile(user, pass));
            return "valid login";
        }

    }

    private boolean isValidUsername(){
        return !username.isEmpty();
    }

    private boolean isValidPassword(){
        return !password.isEmpty();
    }

    // TODO: Implement actual method.
    private boolean isValidLogin(){
        return (username.equals("admin") && password.equals("admin"));
    }


}
