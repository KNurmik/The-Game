package com.example.phase1activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phase1activity.Profile.AppManager;

public abstract class AbstractActivities extends AppCompatActivity {
    public AppManager app;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (AppManager) getApplication();
    }


}
