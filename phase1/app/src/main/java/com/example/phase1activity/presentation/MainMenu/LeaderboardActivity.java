package com.example.phase1activity.presentation.MainMenu;

import android.os.Bundle;
import android.widget.TextView;

import com.example.phase1activity.AbstractActivities;
import com.example.phase1activity.R;

public class LeaderboardActivity extends AbstractActivities {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        TextView text = findViewById(R.id.yourstats);
        String temp = app.getProfile().getNickname() + "'s Statistics";
        text.setText(temp);


    }
}
