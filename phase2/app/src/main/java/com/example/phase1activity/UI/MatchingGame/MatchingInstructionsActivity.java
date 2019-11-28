/*
 * Copyright 2019. Mark Vartolaş, Karl Hendrik Nurmeots, Olivia Li, Ramzi Dajani, Henry Leung.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
