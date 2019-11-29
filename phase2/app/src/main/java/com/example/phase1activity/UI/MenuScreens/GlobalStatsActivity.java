package com.example.phase1activity.UI.MenuScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase1activity.Core.Transmission.Overseers.GlobalStats;
import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivity;

import java.util.ArrayList;
import java.util.List;

public class GlobalStatsActivity extends AbstractActivity {
    /** An instance of a GlobalStats that updates sortedUsers */
    GlobalStats globalStats;

    /** A sorted list of users to their high scores, by best high scores. The stat that high scores
     * are based on is dependent on the user's choice in the UI. */
    List<List<Object>> sortedUsers;

    TextView firstPlace;
    TextView secondPlace;
    TextView thirdPlace;
    TextView fourthPlace;
    TextView fifthPlace;

    List<TextView> textViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_stats);
        globalStats = new GlobalStats(this);

        firstPlace = findViewById(R.id.firstPlace);
        secondPlace = findViewById(R.id.secondPlace);
        thirdPlace = findViewById(R.id.thirdPlace);
        fourthPlace = findViewById(R.id.fourthPlace);
        fifthPlace = findViewById(R.id.fifthPlace);

        textViews = new ArrayList<>();
        textViews.add(firstPlace);
        textViews.add(secondPlace);
        textViews.add(thirdPlace);
        textViews.add(fourthPlace);
        textViews.add(fifthPlace);

        firstPlace.setText("-");
        secondPlace.setText("-");
        thirdPlace.setText("-");
        fourthPlace.setText("-");
        fifthPlace.setText("-");

        Button sortByScore = findViewById(R.id.sortByScore);
        Button sortByMoves = findViewById(R.id.sortByMoves);
        Button sortByReaction = findViewById(R.id.sortByReaction);

        sortByScore.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sortByScore();
                    }
                });

        sortByMoves.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sortByMoves();
                    }
                });

        sortByReaction.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sortByReaction();
                    }
                });

//        Button backButton = findViewById(R.id.backButton);
//        backButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(GlobalStatsActivity.this, StartActivity.class));
//                    }
//                });
    }

    void sortByScore() {
        globalStats.updateGlobalStats();
        this.sortedUsers = globalStats.usersWithBestScores;
        setTextFields();
    }

    void sortByMoves() {
        globalStats.updateGlobalStats();
        this.sortedUsers = globalStats.usersWithMostMoves;
        setTextFields();
    }

    void sortByReaction() {
        globalStats.updateGlobalStats();
        this.sortedUsers = globalStats.usersWithFastestReactions   ;
        setTextFields();
    }

    void setTextFields() {
        for (int i = 0; i < textViews.size(); i++) {
            if (i < sortedUsers.size()) {
                textViews.get(i).setText(sortedUsers.get(i).toString());
            } else {
                textViews.get(i).setText("-");
            }
        }
    }
}
