package com.example.phase1activity.UI.MatchingGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivity;

public class MatchingInstructionsActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_instructions);

        ImageView instructions = findViewById(R.id.matchInstructions);
        Button btn = findViewById(R.id.startMatching);
        colourButton(btn, R.drawable.start_red, R.drawable.start_blue, R.drawable.start_green);
        colourButton(instructions, R.drawable.match_instructions_red, R.drawable.match_instructions_blue, R.drawable.match_instructions_green);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    /** Allow user to continue to the game using the button. */
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MatchingInstructionsActivity.this, ChooseLevelActivity.class));
                    }
                });
    }
}
