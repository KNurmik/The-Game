package com.example.phase1activity.presentation.LogIn;

import android.content.Context;

import com.example.phase1activity.Profile.AppManager;

interface LogInInterface {
    String logInAction(Context context, String user, String pass, AppManager app);
}
