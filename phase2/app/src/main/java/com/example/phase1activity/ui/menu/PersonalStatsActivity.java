package com.example.phase1activity.ui.menu;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import com.example.phase1activity.R;
        import com.example.phase1activity.ui.abstraction.AbstractActivity;

/**
 * Interface that shows the user's statistics.
 */
public class PersonalStatsActivity extends AbstractActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        //Displays Total Score
        TextView total = findViewById(R.id.total);
        String temp1 = "Total score: " + app.getProfileTotalScoreStat();
        total.setText(temp1);

        //Displays Total Moves
        TextView moves = findViewById(R.id.moves);
        String temp2 = "Total moves: " + app.getProfileTotalMovesStat();
        moves.setText(temp2);

        //Displays Fastest Reaction Time
        TextView reaction = findViewById(R.id.reaction);
        String temp3 = "Fastest reaction: " + app.getProfileFastestRxnStat() + " s";
        reaction.setText(temp3);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(PersonalStatsActivity.this, StartActivity.class));
                    }
                });

    }
}
