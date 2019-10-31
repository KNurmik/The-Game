package com.example.phase1activity.presentation.LogIn;

import com.example.phase1activity.Profile.AppManager;

interface LogInInterface {
    abstract String logInAction(String user, String pass, AppManager app);
}
