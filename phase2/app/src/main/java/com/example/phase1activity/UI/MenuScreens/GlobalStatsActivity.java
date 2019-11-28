package com.example.phase1activity.UI.MenuScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivity;

public class GlobalStatsActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_stats);

        TextView top = findViewById(R.id.globalstats);
        String temp = app.getProfileNickname() + "'s Statistics";
        top.setText(temp);

        //Displays Total Score
        TextView total = findViewById(R.id.globaltotal);
        String temp1 = "Total score: " + app.getBestTotal();
        total.setText(temp1);

        //Displays Total Moves
        TextView moves = findViewById(R.id.globalmoves);
        String temp2 = "Total moves: " + app.getBestMoves();
        moves.setText(temp2);

        //Displays Fastest Reaction Time
        TextView reaction = findViewById(R.id.globalreaction);
        String temp3 = "Fastest reaction: " + app.getBestReaction() + " seconds";
        reaction.setText(temp3);
    }
}
