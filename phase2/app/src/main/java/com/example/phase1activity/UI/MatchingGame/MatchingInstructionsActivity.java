package com.example.phase1activity.UI.MatchingGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivities;
import com.example.phase1activity.UI.ReactionGame.ReactionGameView;
import com.example.phase1activity.UI.ReactionGame.ReactionInstructionsActivity;

public class MatchingInstructionsActivity extends AbstractActivities {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_instructions);

        Button btn = findViewById(R.id.startMatching);
        colourButton(btn, R.drawable.start_red, R.drawable.start_blue, R.drawable.start_green);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    /** Allow user to continue to the game using the button. */
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MatchingInstructionsActivity.this, MatchingGameActivity.class));
                    }
                });
    }
}
