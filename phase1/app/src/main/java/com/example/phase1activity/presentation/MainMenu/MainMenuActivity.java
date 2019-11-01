package com.example.phase1activity.presentation.MainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.phase1activity.Profile.AppManager;
import com.example.phase1activity.R;

public class MainMenuActivity extends AppCompatActivity {
    AppManager app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        app = (AppManager) getApplication();

    }
}
