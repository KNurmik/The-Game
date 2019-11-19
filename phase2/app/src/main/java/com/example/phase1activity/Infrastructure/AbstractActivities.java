package com.example.phase1activity.Infrastructure;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phase1activity.Domain.AppManager;

/**
 * Base activity, every Activity in the application inherits this class
 */
public abstract class AbstractActivities extends AppCompatActivity {
    public AppManager app;

    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (AppManager) getApplication();
    }


}
