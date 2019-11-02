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
        TextView top = findViewById(R.id.yourstats);
        String temp = app.getProfileNickname() + "'s Statistics";
        top.setText(temp);

        TextView total = findViewById(R.id.total);
        String temp1 = "Total score: " + app.getProfile().getTotalScoreStat();
        total.setText(temp1);

        TextView moves = findViewById(R.id.moves);
        String temp2 = "Total moves: " + app.getProfile().getTotalMovesStat();
        moves.setText(temp2);

        TextView reaction = findViewById(R.id.reaction);
        String temp3 = "Fastest reaction: " + app.getProfile().getFastestRxnStat() + " seconds";
        reaction.setText(temp3);


    }
}
